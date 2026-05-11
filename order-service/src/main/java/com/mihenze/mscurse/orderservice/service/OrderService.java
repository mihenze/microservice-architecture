package com.mihenze.mscurse.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.dtocommon.kafka.AddressData;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatus;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.dtocommon.kafka.PaymentData;
import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.DeliveryMethod;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.orderservice.config.BindingProperties;
import com.mihenze.mscurse.orderservice.dto.OrderDto;
import com.mihenze.mscurse.orderservice.entity.Order;
import com.mihenze.mscurse.orderservice.entity.OrderItem;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageStatus;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageType;
import com.mihenze.mscurse.orderservice.exception.InvalidUpdateOrderException;
import com.mihenze.mscurse.orderservice.exception.NotFoundOrderException;
import com.mihenze.mscurse.orderservice.mapper.DeliveryAddressMapper;
import com.mihenze.mscurse.orderservice.mapper.OrderMapper;
import com.mihenze.mscurse.orderservice.repository.OrderRepository;
import com.mihenze.mscurse.orderservice.util.UidGenerateService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final UidGenerateService uidGenerateService;
    private final AsyncMessageService asyncMessageService;
    private final BindingProperties bindingProperties;
    private final ObjectMapper mapper;

    @CircuitBreaker(name = "orderServiceCircuitBreaker", fallbackMethod = "fallbackCreateOrder")
    @Transactional
    public OrderDto createOrder(OrderDto request) {
        Order order = orderMapper.mapToOrder(request);

        order.setStatus(OrderStatus.CREATED);
        order.setUid(uidGenerateService.generateUid());

        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }

        Order orderSaved = orderRepository.save(order);

        // сформируем сообщение для оплаты
        createAndSaveOrderPaymentMessage(orderSaved.getId(), orderSaved.getCost());

        return orderMapper.mapToOrderDto(orderSaved);
    }

    @Transactional
    public OrderDto updateOrder(OrderDto request) {

        if (request.getStatus() == OrderStatus.CREATED) {
            Order order = orderRepository.findByIdFetch(request.getId())
                    .orElseThrow(() -> new NotFoundOrderException(request.getId()));

            Order orderUpd = orderMapper.mapToOrder(request);
            order.setCost(orderUpd.getCost());
            order.setAddress(orderUpd.getAddress());
            order.getItems().clear();

            if (orderUpd.getItems() != null) {
                for (OrderItem item : orderUpd.getItems()) {
                    item.setOrder(order);
                    order.getItems().add(item);
                }
            }

            Order saved = orderRepository.save(order);
            return orderMapper.mapToOrderDto(saved);
        } else {
            throw new InvalidUpdateOrderException(request.getStatus());
        }
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundOrderException(id));

        return orderMapper.mapToOrderDto(order);
    }

    public List<OrderDto> getAllOrder() {
        List<Order> orders = orderRepository.findAllFetch();
        return orders.stream().map(orderMapper::mapToOrderDto).toList();
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundOrderException(id);
        }

        orderRepository.deleteById(id);
    }

    @Transactional
    public OrderDto fallbackCreateOrder(OrderDto request, Throwable ex) {
        log.error("Payment service unavailable. Fallback triggered for createOrder", ex);

        // создаём заказ БЕЗ оплаты
        Order order = orderMapper.mapToOrder(request);
        order.setStatus(OrderStatus.CREATED);
        order.setUid(uidGenerateService.generateUid());

        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }

        Order saved = orderRepository.save(order);

        return orderMapper.mapToOrderDto(saved);
    }

    @Transactional
    public void updateOrderStatus(Long id, OrderStatus orderStatus) {
        Order order = orderRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundOrderException(id));

        order.setStatus(orderStatus);
    }

    private void createAndSaveOrderPaymentMessage(Long orderId, BigDecimal amount) {

        OrderCreationStatusMessage orderCreationStatusMessage = OrderCreationStatusMessage.builder()
                .orderId(orderId)
                .orderCreationStatus(OrderCreationStatus.DRAFT)
                .paymentData(new PaymentData(PaymentType.CARD, amount, Currency.RUB))
                .build();

        createAsyncMessage(orderCreationStatusMessage);
    }

    @Transactional
    public void createAndSaveOrderShipmentMessage(Long orderId, OrderCreationStatusMessage orderCreationStatusMessage) {
        updateOrderStatus(orderId, OrderStatus.PAID);

        OrderDto orderDto = getOrderById(orderId);

        orderCreationStatusMessage.setAddressData(
                deliveryAddressMapper.mapToAddressData(orderDto.getAddress())
        );
        orderCreationStatusMessage.setOrderCreationStatus(OrderCreationStatus.PROCESSED);
        orderCreationStatusMessage.setDeliveryMethod(DeliveryMethod.COURIER);

        createAsyncMessage(orderCreationStatusMessage);
    }

    private void createAsyncMessage(OrderCreationStatusMessage orderCreationStatusMessage) {
        try {
            AsyncMessage asyncMessage = AsyncMessage.builder()
                    .bindingName(bindingProperties.getOrderCreationStatus())
                    .value(mapper.writeValueAsString(orderCreationStatusMessage))
                    .type(AsyncMessageType.OUTBOX)
                    .status(AsyncMessageStatus.CREATED)
                    .idempotencyKey(UUID.randomUUID())
                    .build();

            asyncMessageService.saveMessage(asyncMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

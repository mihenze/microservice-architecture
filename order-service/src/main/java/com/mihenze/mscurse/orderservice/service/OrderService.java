package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.orderservice.dto.OrderDto;
import com.mihenze.mscurse.orderservice.entity.Order;
import com.mihenze.mscurse.orderservice.entity.OrderItem;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import com.mihenze.mscurse.orderservice.exception.InvalidUpdateOrderException;
import com.mihenze.mscurse.orderservice.exception.NotFoundOrderException;
import com.mihenze.mscurse.orderservice.mapper.OrderMapper;
import com.mihenze.mscurse.orderservice.repository.OrderRepository;
import com.mihenze.mscurse.orderservice.util.UidGenerateService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UidGenerateService uidGenerateService;
    private final SenderService senderService;

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

        // сформируем оплату
        senderService.createPayment(orderSaved.getId(), orderSaved.getCost());

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
}

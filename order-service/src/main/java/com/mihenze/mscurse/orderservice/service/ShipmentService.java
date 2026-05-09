package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.enums.DeliveryMethod;
import com.mihenze.mscurse.orderservice.config.KafkaFuncProducer;
import com.mihenze.mscurse.orderservice.dto.OrderDto;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService {

    private final OrderService orderService;
    private final KafkaFuncProducer kafkaFuncProducer;

    public void createShipment(Long orderId) {
        orderService.updateOrderStatus(orderId, OrderStatus.PAID);

        OrderDto orderDto = orderService.getOrderById(orderId);

        CreateShipmentRequest createShipmentRequest = CreateShipmentRequest.builder()
                .orderId(orderId)
                .addressRegion(orderDto.getAddress().getRegion())
                .addressCity(orderDto.getAddress().getCity())
                .addressStreet(orderDto.getAddress().getStreet())
                .addressPostcode(orderDto.getAddress().getPostcode())
                .addressBuilding(orderDto.getAddress().getPostcode())
                .addressApartment(orderDto.getAddress().getApartment())
                .deliveryMethod(DeliveryMethod.COURIER)
                .build();

        kafkaFuncProducer.getOrderStreamForShipment().emitNext(
                MessageBuilder
                        .withPayload(createShipmentRequest)
                        .build(), Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

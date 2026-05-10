package com.mihenze.mscurse.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.dtocommon.rest.enums.AsyncEventType;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.enums.DeliveryMethod;
import com.mihenze.mscurse.orderservice.config.BindingProperties;
import com.mihenze.mscurse.orderservice.dto.OrderDto;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageStatus;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< task11-idempotent-event

import java.util.UUID;
=======
>>>>>>> main

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService {

    private final OrderService orderService;
    private final AsyncMessageService asyncMessageService;
    private final BindingProperties bindingProperties;
    private final ObjectMapper mapper;

    @Transactional
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


        createAndSaveOrderShipmentMessage(createShipmentRequest);

    }

    private void createAndSaveOrderShipmentMessage(CreateShipmentRequest createShipmentRequest) {

        try {
            AsyncMessage asyncMessage = AsyncMessage.builder()
                    .bindingName(bindingProperties.getShipmentCreated())
                    .value(mapper.writeValueAsString(createShipmentRequest))
                    .payloadType(AsyncEventType.SHIPMENT_CREATED)
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

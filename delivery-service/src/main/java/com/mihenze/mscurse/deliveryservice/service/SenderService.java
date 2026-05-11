package com.mihenze.mscurse.deliveryservice.service;

import com.mihenze.mscurse.deliveryservice.config.KafkaFuncProducer;
import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatus;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.dtocommon.kafka.PaymentData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {
    private final KafkaFuncProducer kafkaFuncProducer;
    private final ShipmentMapper shipmentMapper;

    public void sendShipmentInfo(ShipmentDto shipmentDto, OrderCreationStatus status) {
        OrderCreationStatusMessage orderCreationStatusMessage = OrderCreationStatusMessage
                .builder()
                .orderId(shipmentDto.getOrderId())
                .shipmentId(shipmentDto.getId())
                .addressData(shipmentMapper.mapToAddressData(shipmentDto))
                .orderCreationStatus(status)
                .build();

        kafkaFuncProducer.getShipmentStream().emitNext(
                MessageBuilder
                        .withPayload(orderCreationStatusMessage)
                        .setHeader("X-Idempotency-Key", UUID.randomUUID().toString())
                        .build(),
                Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

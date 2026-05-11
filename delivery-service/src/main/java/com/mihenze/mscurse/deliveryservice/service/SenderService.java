package com.mihenze.mscurse.deliveryservice.service;

import com.mihenze.mscurse.deliveryservice.config.KafkaFuncProducer;
import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
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

    public void sendShipmentInfo(ShipmentDto shipmentDto) {
        kafkaFuncProducer.getShipmentStream().emitNext(
                MessageBuilder
                        .withPayload(shipmentMapper.mapToShipmentResponse(shipmentDto))
                        .setHeader("X-Idempotency-Key", UUID.randomUUID().toString())
                        .build(),
                Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

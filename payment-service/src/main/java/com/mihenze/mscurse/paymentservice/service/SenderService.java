package com.mihenze.mscurse.paymentservice.service;

import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatus;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.dtocommon.kafka.PaymentData;
import com.mihenze.mscurse.paymentservice.config.KafkaFuncProducer;
import com.mihenze.mscurse.paymentservice.dto.PaymentDto;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SenderService {
    private final KafkaFuncProducer kafkaFuncProducer;
    private final PaymentMapper paymentMapper;

    public void sendPaymentInfo(PaymentDto paymentDto, OrderCreationStatus status) {
        OrderCreationStatusMessage orderCreationStatusMessage = OrderCreationStatusMessage
                .builder()
                .orderId(paymentDto.getOrderId())
                .paymentId(paymentDto.getId())
                .paymentData(new PaymentData(paymentDto.getType(), paymentDto.getAmount(), paymentDto.getCurrency()))
                .orderCreationStatus(status)
                .build();

        kafkaFuncProducer.getPaymentStream().emitNext(
                MessageBuilder
                        .withPayload(orderCreationStatusMessage)
                        .setHeader("X-Idempotency-Key", UUID.randomUUID().toString())
                        .build(),
                Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

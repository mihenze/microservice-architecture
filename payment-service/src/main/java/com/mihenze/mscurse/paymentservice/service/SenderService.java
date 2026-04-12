package com.mihenze.mscurse.paymentservice.service;

import com.mihenze.mscurse.paymentservice.config.RabbitFuncProducer;
import com.mihenze.mscurse.paymentservice.dto.PaymentDto;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class SenderService {
    private final RabbitFuncProducer rabbitFuncProducer;
    private final PaymentMapper paymentMapper;

    public void sendPaymentInfo(PaymentDto paymentDto) {
        rabbitFuncProducer.getPaymentStream().emitNext(
                MessageBuilder
                        .withPayload(paymentMapper.mapToPaymentResponse(paymentDto))
                        .build(), Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

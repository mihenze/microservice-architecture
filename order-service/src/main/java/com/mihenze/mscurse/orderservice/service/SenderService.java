package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.orderservice.config.RabbitFuncProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SenderService {
    private final RabbitFuncProducer rabbitFuncProducer;

    public void createPayment(Long orderId, BigDecimal amount) {

        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .type(PaymentType.CARD)
                .amount(amount)
                .currency(Currency.RUB)
                .orderId(orderId)
                .build();

        rabbitFuncProducer.getOrderStream().emitNext(
                MessageBuilder
                        .withPayload(createPaymentRequest)
                        .build(), Sinks.EmitFailureHandler.FAIL_FAST);
    }
}

package com.mihenze.mscurse.paymentservice.config;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import com.mihenze.mscurse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitFuncConsumer {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Bean
    public Consumer<Message<CreatePaymentRequest>> orderConsume() {
        return message ->
                paymentService.createPayment(paymentMapper.mapToPaymentDto(message.getPayload()));
    }
}

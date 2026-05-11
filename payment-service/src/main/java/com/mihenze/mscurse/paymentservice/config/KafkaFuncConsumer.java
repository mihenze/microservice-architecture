package com.mihenze.mscurse.paymentservice.config;

import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
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
public class KafkaFuncConsumer {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Bean
    public Consumer<Message<OrderCreationStatusMessage>> orderCreationStatusConsumer() {
        return message -> {
            OrderCreationStatusMessage payload = message.getPayload();
            switch (payload.getOrderCreationStatus()) {
                case DRAFT -> paymentService.createPayment(paymentMapper.mapToPaymentDto(message.getPayload()));
                case DELIVERY_CANCELED -> paymentService.refundedPayment(paymentMapper.mapToPaymentDto(message.getPayload()));
                default -> log.info("NOT MY STATUS");
            }
        };

    }
}

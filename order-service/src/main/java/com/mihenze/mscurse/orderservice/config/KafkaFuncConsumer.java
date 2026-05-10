package com.mihenze.mscurse.orderservice.config;

import com.mihenze.mscurse.dtocommon.rest.enums.AsyncEventType;
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
import com.mihenze.mscurse.orderservice.service.ShipmentService;
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

    private final ShipmentService shipmentService;
    private final IdempotentMessageProcessor idempotentMessageProcessor;
    private final BindingProperties bindingProperties;

    @Bean
    public Consumer<Message<PaymentResponse>> paymentConsume() {
        return message -> {

            idempotentMessageProcessor.process(
                    message,
                    payload -> {
                        shipmentService.createShipment(payload.getOrderId());
                        return null;
                    },
                    AsyncEventType.PAYMENT_CONFIRM,
                    bindingProperties.getPaymentConfirm()
            );
        };
    }

    @Bean
    public Consumer<Message<ShipmentResponse>> shipmentConsume() {
        return message -> {

            idempotentMessageProcessor.process(
                    message,
                    payload -> {
                        log.info("Shipment = {}", payload);
                        return null;
                    },
                    AsyncEventType.SHIPMENT_CONFIRM,
                    bindingProperties.getShipmentConfirm()
            );
        };
    }
}

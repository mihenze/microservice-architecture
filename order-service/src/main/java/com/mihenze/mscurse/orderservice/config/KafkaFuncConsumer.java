package com.mihenze.mscurse.orderservice.config;

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

    @Bean
    public Consumer<Message<PaymentResponse>> paymentConsume() {
        return message -> {
            log.info("Payment = {}", message.getPayload());
            shipmentService.createShipment(message.getPayload().getOrderId());
        };
    }

    @Bean
    public Consumer<Message<ShipmentResponse>> shipmentConsume() {
        return message -> {
            log.info("Shipment = {}", message.getPayload());
        };
    }
}

package com.mihenze.mscurse.deliveryservice.config;

import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.deliveryservice.service.ShipmentService;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatus;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Objects;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaFuncConsumer {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;

    @Bean
    public Consumer<Message<OrderCreationStatusMessage>> orderCreationStatusConsumer() {
        return message -> {
            OrderCreationStatusMessage payload = message.getPayload();
            if (Objects.requireNonNull(payload.getOrderCreationStatus()) == OrderCreationStatus.PROCESSED) {
                shipmentService.createShipment(shipmentMapper.mapToShipmentDto(message.getPayload()));
            } else {
                log.info("NOT MY STATUS");
            }
        };
    }
}

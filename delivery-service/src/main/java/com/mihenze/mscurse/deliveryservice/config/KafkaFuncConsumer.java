package com.mihenze.mscurse.deliveryservice.config;

import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.deliveryservice.service.ShipmentService;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
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
    private final ShipmentMapper shipmentMapper;

    @Bean
    public Consumer<Message<CreateShipmentRequest>> shipmentConsume() {
        return message ->
                shipmentService.createShipment(shipmentMapper.mapToShipmentDto(message.getPayload()));
    }
}

package com.mihenze.mscurse.orderservice.config;

import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
@Getter
public class KafkaFuncProducer {

    private Sinks.Many<Message<CreateShipmentRequest>> orderStreamForShipment =
            Sinks.many().multicast().onBackpressureBuffer();

    @Bean
    public Supplier<Flux<Message<CreateShipmentRequest>>> orderProduceShipment() {
        return () -> orderStreamForShipment.asFlux(); //считываем данные из потока Flux
    }
}

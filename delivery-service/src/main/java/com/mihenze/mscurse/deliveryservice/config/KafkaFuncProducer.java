package com.mihenze.mscurse.deliveryservice.config;

import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.function.Supplier;

@Configuration
@Getter
public class KafkaFuncProducer {
    private Sinks.Many<Message<ShipmentResponse>> shipmentStream =
            Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    @Bean
    public Supplier<Flux<Message<ShipmentResponse>>> shipmentProduce() {
        return () -> shipmentStream.asFlux(); //считываем данные из потока Flux
    }
}

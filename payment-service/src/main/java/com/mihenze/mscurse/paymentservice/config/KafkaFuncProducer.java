package com.mihenze.mscurse.paymentservice.config;

import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
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
    private Sinks.Many<Message<OrderCreationStatusMessage>> paymentStream =
            Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    @Bean
    public Supplier<Flux<Message<OrderCreationStatusMessage>>> orderCreationStatusProducer() {
        return () -> paymentStream.asFlux(); //считываем данные из потока Flux
    }
}

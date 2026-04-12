package com.mihenze.mscurse.orderservice.config;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import lombok.Getter;
import org.springframework.messaging.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.function.Supplier;

@Configuration
@Getter
public class RabbitFuncProducer {

    private Sinks.Many<Message<CreatePaymentRequest>> orderStream =
            Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    @Bean
    public Supplier<Flux<Message<CreatePaymentRequest>>> orderProduce() {
        return () -> orderStream.asFlux(); //считываем данные из потока Flux
    }
}

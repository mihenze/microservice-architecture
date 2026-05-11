package com.mihenze.mscurse.orderservice.config;

import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import com.mihenze.mscurse.orderservice.service.OrderService;
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

    private final OrderService orderService;
    private final IdempotentMessageProcessor idempotentMessageProcessor;
    private final BindingProperties bindingProperties;

    @Bean
    public Consumer<Message<OrderCreationStatusMessage>> orderCreationStatus() {
        return message -> {

            idempotentMessageProcessor.process(
                    message,
                    payload -> {
                        switch (payload.getOrderCreationStatus()) {
                            case PAYMENT_WAITING -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.PAYMENT_AWAITING);
                            case PAYMENT_CONFIRM -> orderService.createAndSaveOrderShipmentMessage(payload.getOrderId(), payload);
                            case PAYMENT_ABORT -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.NOT_PAID);
                            case PAYMENT_REFUNDED -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.CANCELED);
                            case DELIVERY_CREATED -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.DELIVERY_AWAITING);
                            case DELIVERY_CANCELED -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.REFUND_PAID);
                            case DELIVERY_COMPLETED -> orderService.updateOrderStatus(payload.getOrderId(),
                                    OrderStatus.COMPLETED);
                            default -> {
                                log.info("NOT MY STATUS");
                                return null;
                            }
                        }
                        return null;
                    },
                    bindingProperties.getOrderCreationStatus()
            );
        };
    }
}

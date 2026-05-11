package com.mihenze.mscurse.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OutboxPublisher {
    private final StreamBridge streamBridge;
    private final ObjectMapper mapper;

    public void publish(AsyncMessage message) {
        try {

            Object payload = mapper.readValue(message.getValue(), OrderCreationStatusMessage.class);

            boolean isSent = streamBridge.send(
                    message.getBindingName(),
                    MessageBuilder
                            .withPayload(payload)
                            .setHeader("contentType", "application/json")
                            .build()
            );

            if (!isSent) {
                throw new RuntimeException("Message not sent");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish message", e);
        }
    }
}

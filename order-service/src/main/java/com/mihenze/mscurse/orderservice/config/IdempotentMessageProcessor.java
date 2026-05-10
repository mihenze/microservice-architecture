package com.mihenze.mscurse.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.dtocommon.rest.enums.AsyncEventType;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageStatus;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageType;
import com.mihenze.mscurse.orderservice.service.AsyncMessageService;
import com.mihenze.mscurse.orderservice.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class IdempotentMessageProcessor {
    private final AsyncMessageService asyncMessageService;
    private final ObjectMapper mapper;

    public <T> boolean process(Message<T> message, Function<T, Void> handler, AsyncEventType eventType, String topic) {

        String idempotencyKey =
                HeaderUtils.getString(message, "X-Idempotency-Key");

        if (idempotencyKey == null) {
            log.error("Missing idempotency key");
            return false;
        }

        AsyncMessage inbox = AsyncMessage.builder()
                .bindingName(topic)
                .payloadType(eventType)
                .value(serialize(message.getPayload()))
                .type(AsyncMessageType.INBOX)
                .status(AsyncMessageStatus.RECEIVED)
                .idempotencyKey(UUID.fromString(idempotencyKey))
                .build();

        try {
            asyncMessageService.saveMessage(inbox);
        } catch (Exception e) {
            log.warn("Message with the same idempotent key is present in DB: {}", idempotencyKey);
            return false;
        }

        handler.apply(message.getPayload());
        return true;
    }

    private String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

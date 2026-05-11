package com.mihenze.mscurse.orderservice.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.service.AsyncMessageService;
import com.mihenze.mscurse.orderservice.service.OutboxPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс для обработки и отправки асинхронных сообщений через Kafka.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncMessageSenderProcessor {
    private final AsyncMessageService asyncMessageService;
    private final OutboxPublisher publisher;

    /**
     * Отправляет асинхронное сообщение через Kafka и обновляет его статус.
     * Оборачивается в транзакцию для обеспечения атомарности.
     *
     * @param message сообщение, которое необходимо отправить
     */
    @Transactional
    public void sendMessage(AsyncMessage message) {
            publisher.publish(message);
            asyncMessageService.markAsSent(message);
    }
}

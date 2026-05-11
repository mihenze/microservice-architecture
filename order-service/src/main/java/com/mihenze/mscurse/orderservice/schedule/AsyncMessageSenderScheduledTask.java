package com.mihenze.mscurse.orderservice.schedule;

import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.service.AsyncMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Задача для периодической отправки асинхронных сообщений.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncMessageSenderScheduledTask {
    private final AsyncMessageService asyncMessageService;
    private final AsyncMessageSenderProcessor processor;

    /**
     * Метод, запускаемый по расписанию, который получает неподтвержденные сообщения и отправляет их.
     */
    @Scheduled(fixedDelay = 3000)
    public void sendOutboxMessages() {
        List<AsyncMessage> messages = asyncMessageService.getUnsentOutboxMessages(50);
        log.info("List async: {}", messages.size());

        for (AsyncMessage message : messages) {
            processor.sendMessage(message);
        }
    }
}

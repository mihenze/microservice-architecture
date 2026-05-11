package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageStatus;
import com.mihenze.mscurse.orderservice.repository.AsyncMessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для работы с асинхронными сообщениями.
 */
@Service
@RequiredArgsConstructor
public class AsyncMessageService {
    private final AsyncMessageRepository repository;

    /**
     * Сохраняет сообщение в базе данных.
     *
     * @param message сообщение для сохранения
     */
    @Transactional
    public void saveMessage(AsyncMessage message) {
        repository.save(message);
    }

    /**
     * Получает список неподтвержденных (неотправленных) сообщений с ограничением по размеру батча.
     *
     * @param batchSize максимальное количество сообщений
     * @return список неподтвержденных сообщений
     */
    public List<AsyncMessage> getUnsentOutboxMessages(int batchSize) {
        Pageable pageable = Pageable.ofSize(batchSize);
        return repository.findUnsentOutboxMessages(pageable);
    }

    /**
     * Обновляет статус сообщения на "Отправлено" и сохраняет изменение.
     *
     * @param message сообщение, которое нужно пометить как отправленное
     */
    public void markAsSent(AsyncMessage message) {
        message.setStatus(AsyncMessageStatus.SENT);
        repository.save(message);
    }
}

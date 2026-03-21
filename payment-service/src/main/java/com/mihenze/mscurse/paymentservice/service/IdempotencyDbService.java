package com.mihenze.mscurse.paymentservice.service;

import com.mihenze.mscurse.paymentservice.entity.IdempotencyEntity;
import com.mihenze.mscurse.paymentservice.entity.IdempotencyStatus;
import com.mihenze.mscurse.paymentservice.repository.IdempotencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdempotencyDbService {

    private final IdempotencyRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<IdempotencyEntity> tryCreate(String key) {
        if (repository.findByIdempotencyKey(key).isPresent()) {
            return repository.findByIdempotencyKey(key);
        }
        IdempotencyEntity entity = IdempotencyEntity.builder()
                .idempotencyKey(key)
                .idempotencyStatus(IdempotencyStatus.PENDING)
                .createdAt(Instant.now())
                .build();
        repository.save(entity);
        return Optional.empty();
    }

    @Transactional
    public void markCompleted(String key, String response, int statusCode) {
        repository.findByIdempotencyKey(key).ifPresent(entity -> {
            entity.setIdempotencyStatus(IdempotencyStatus.COMPLETED);
            entity.setResponse(response);
            entity.setStatusCode(statusCode);
            entity.setUpdatedAt(Instant.now());
            repository.save(entity);
        });
    }
}

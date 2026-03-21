package com.mihenze.mscurse.paymentservice.repository;

import com.mihenze.mscurse.paymentservice.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface IdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {
    @Lock(PESSIMISTIC_WRITE)
    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}

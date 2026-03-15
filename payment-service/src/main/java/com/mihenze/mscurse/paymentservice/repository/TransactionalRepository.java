package com.mihenze.mscurse.paymentservice.repository;

import com.mihenze.mscurse.paymentservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<Transaction, Long> {
    boolean existsById(Long id);
}

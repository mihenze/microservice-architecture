package com.mihenze.mscurse.paymentservice.repository;

import com.mihenze.mscurse.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "from Payment pmn left join fetch pmn.transactions where pmn.id=:id")
    Optional<Payment> findByIdFetch(Long id);

    boolean existsById(Long id);
}

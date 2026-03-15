package com.mihenze.mscurse.paymentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Transactional's status must not be null")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "type", nullable = false)
    @NotNull(message = "Transactional's type must not be null")
    @Enumerated(EnumType.STRING)
    private TransactionalType type;

    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    @NotNull(message = "Transactional's amount must not be null")
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @NotNull(message = "Payment's currency must not be null")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
}

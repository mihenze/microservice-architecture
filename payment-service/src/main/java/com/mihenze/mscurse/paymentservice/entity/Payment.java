package com.mihenze.mscurse.paymentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Payment's status must not be null")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "type", nullable = false)
    @NotNull(message = "Payment's type must not be null")
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    @NotNull(message = "Payment's amount must not be null")
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @NotNull(message = "Payment's currency must not be null")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "order_id", nullable = false)
    @NotNull(message = "Payment's orderId must not be null")
    private Long orderId;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Transaction> transactions;
}

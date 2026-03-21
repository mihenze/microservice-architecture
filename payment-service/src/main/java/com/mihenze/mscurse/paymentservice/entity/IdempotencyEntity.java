package com.mihenze.mscurse.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "idempotency", uniqueConstraints = @UniqueConstraint(columnNames = "idempotency_key"))
public class IdempotencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdempotencyStatus idempotencyStatus;

    @Lob
    @Column(name = "response")
    private String response;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}

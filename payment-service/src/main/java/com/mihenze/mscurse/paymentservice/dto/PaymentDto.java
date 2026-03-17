package com.mihenze.mscurse.paymentservice.dto;

import com.mihenze.mscurse.paymentservice.entity.Currency;
import com.mihenze.mscurse.paymentservice.entity.PaymentStatus;
import com.mihenze.mscurse.paymentservice.entity.PaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
    Long id;
    PaymentStatus status;
    PaymentType type;
    BigDecimal amount;
    Currency currency;
    Instant createdAt;
    Instant updatedAt;
    Long orderId;
    List<TransactionDto> transactions;
}

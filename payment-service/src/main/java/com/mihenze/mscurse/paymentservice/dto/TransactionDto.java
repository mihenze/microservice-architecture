package com.mihenze.mscurse.paymentservice.dto;

import com.mihenze.mscurse.paymentservice.entity.Currency;
import com.mihenze.mscurse.paymentservice.entity.TransactionStatus;
import com.mihenze.mscurse.paymentservice.entity.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {
    Long id;
    TransactionStatus status;
    TransactionType type;
    BigDecimal amount;
    Currency currency;
    Instant createdAt;
}

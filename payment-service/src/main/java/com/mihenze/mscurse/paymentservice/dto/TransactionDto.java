package com.mihenze.mscurse.paymentservice.dto;

import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionStatus;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {
    Long id;
    TransactionStatus status;
    TransactionType type;
    BigDecimal amount;
    Currency currency;
    Instant createdAt;
}

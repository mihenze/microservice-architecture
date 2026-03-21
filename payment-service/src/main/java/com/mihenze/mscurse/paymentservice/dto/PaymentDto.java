package com.mihenze.mscurse.paymentservice.dto;

import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentStatus;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
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

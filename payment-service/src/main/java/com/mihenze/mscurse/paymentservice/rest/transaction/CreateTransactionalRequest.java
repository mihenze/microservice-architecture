package com.mihenze.mscurse.paymentservice.rest.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.paymentservice.entity.Currency;
import com.mihenze.mscurse.paymentservice.entity.TransactionalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("CreateTransactionalRequest")
@Schema(description = "Запрос на создание новой транзакции")
public class CreateTransactionalRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Тип транзакции", example = "AUTHORIZATION", implementation = TransactionalType.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    TransactionalType type;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Цена", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal amount;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Валюта", example = "USD", implementation = Currency.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    Currency currency;
}

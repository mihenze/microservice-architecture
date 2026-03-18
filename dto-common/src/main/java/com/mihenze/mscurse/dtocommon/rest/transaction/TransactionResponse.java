package com.mihenze.mscurse.dtocommon.rest.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionStatus;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("TransactionalResponse")
@Schema(description = "Текущая транзакция")
public class TransactionResponse {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор транзакции", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние транзакции", example = "PENDING", implementation = TransactionStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    TransactionStatus status;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Тип транзакции", example = "AUTHORIZATION", implementation = TransactionType.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    TransactionType type;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Цена", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal amount;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Валюта", example = "USD", implementation = Currency.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    Currency currency;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Время создания", example = "2025-09-25T01:11:36Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    Instant createdAt;
}

package com.mihenze.mscurse.paymentservice.rest.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.paymentservice.entity.TransactionStatus;
import com.mihenze.mscurse.paymentservice.entity.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("UpdateTransactionalRequest")
@Schema(description = "Запрос на изменение статуса транзакции")
public class UpdateTransactionRequest {
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
}

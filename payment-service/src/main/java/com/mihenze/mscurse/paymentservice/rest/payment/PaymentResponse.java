package com.mihenze.mscurse.paymentservice.rest.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.paymentservice.entity.Currency;
import com.mihenze.mscurse.paymentservice.entity.PaymentStatus;
import com.mihenze.mscurse.paymentservice.entity.PaymentType;
import com.mihenze.mscurse.paymentservice.rest.transaction.TransactionalResponse;
import io.swagger.v3.oas.annotations.media.Schema;
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
@JsonTypeName("PaymentResponse")
@Schema(description = "Текущая оплата")
public class PaymentResponse {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор оплаты", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние оплаты", example = "PENDING", implementation = PaymentStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentStatus status;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Тип оплаты", example = "CARD", implementation = PaymentType.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentType type;

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

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Время изменения", example = "2025-09-25T01:11:36Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    Instant updatedAt;

    @Schema(description = "Уникальный идентификатор заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    Long orderId;

    @Schema(description = "Список транзакций оплаты", requiredMode = Schema.RequiredMode.REQUIRED)
    List<TransactionalResponse> transactions;
}

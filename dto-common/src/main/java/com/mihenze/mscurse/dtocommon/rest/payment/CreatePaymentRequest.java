package com.mihenze.mscurse.dtocommon.rest.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("CreatePaymentRequest")
@Schema(description = "Запрос на создание новой оплаты")
public class CreatePaymentRequest {
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

    @Schema(description = "Уникальный идентификатор заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    Long orderId;
}

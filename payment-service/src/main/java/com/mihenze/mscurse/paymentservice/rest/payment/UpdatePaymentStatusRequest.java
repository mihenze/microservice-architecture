package com.mihenze.mscurse.paymentservice.rest.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;

import com.mihenze.mscurse.dtocommon.rest.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("UpdatePaymentStatusRequest")
@Schema(description = "Изменение состояния оплаты")
public class UpdatePaymentStatusRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор оплаты", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние оплаты", example = "PENDING", implementation = PaymentStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    PaymentStatus status;
}

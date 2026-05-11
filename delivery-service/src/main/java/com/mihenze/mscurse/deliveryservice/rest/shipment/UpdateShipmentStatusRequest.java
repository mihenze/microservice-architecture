package com.mihenze.mscurse.deliveryservice.rest.shipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.dtocommon.rest.enums.ShipmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("UpdateShipmentStatusRequest")
@Schema(description = "Обновить статус текущего отправления")
public class UpdateShipmentStatusRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор отправления", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние оплаты", example = "CREATED", implementation = ShipmentStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    ShipmentStatus status;
}

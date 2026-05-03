package com.mihenze.mscurse.deliveryservice.rest.shipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.dtocommon.rest.enums.DeliveryMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("UpdateShipmentRequest")
@Schema(description = "Обновить текущее отправление")
public class UpdateShipmentRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор отправления", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование территориального региона доставки", requiredMode = Schema.RequiredMode.REQUIRED)
    String addressRegion;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование города", requiredMode = Schema.RequiredMode.REQUIRED)
    String addressCity;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование улицы", requiredMode = Schema.RequiredMode.REQUIRED)
    String addressStreet;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Почтовый индекс", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer addressPostcode;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Номер дома", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer addressBuilding;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Номер квартиры", requiredMode = Schema.RequiredMode.REQUIRED, nullable = true)
    Integer addressApartment;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Метод доставки", example = "COURIER", implementation = DeliveryMethod.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    DeliveryMethod deliveryMethod;
}

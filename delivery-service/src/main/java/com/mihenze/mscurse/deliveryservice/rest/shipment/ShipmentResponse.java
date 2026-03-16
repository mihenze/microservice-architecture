package com.mihenze.mscurse.deliveryservice.rest.shipment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.deliveryservice.entity.DeliveryMethod;
import com.mihenze.mscurse.deliveryservice.entity.ShipmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("ShipmentResponse")
@Schema(description = "Текущее отправление")
public class ShipmentResponse {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор отправления", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    Long orderId;

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

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние отправления", example = "CREATED", implementation = ShipmentStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    ShipmentStatus status;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Номер отслеживания", requiredMode = Schema.RequiredMode.REQUIRED)
    String trackingNumber;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Время изменения", example = "2025-09-25T01:11:36Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    Instant createdAt;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Время изменения", example = "2025-09-25T01:11:36Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    Instant updatedAt;

    @Schema(description = "Список посылок", requiredMode = Schema.RequiredMode.REQUIRED)
    List<PackageResponse> packages;

    @Schema(description = "Список отслеживаний", requiredMode = Schema.RequiredMode.REQUIRED)
    List<TrackingEventResponse> events;
}

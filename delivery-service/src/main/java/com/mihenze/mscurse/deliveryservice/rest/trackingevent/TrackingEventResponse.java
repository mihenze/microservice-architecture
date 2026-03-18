package com.mihenze.mscurse.deliveryservice.rest.trackingevent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.deliveryservice.entity.TrackingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("TrackingEventResponse")
@Schema(description = "Текущее отслеживание")
public class TrackingEventResponse {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор отслеживания", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Описание отслеживания", requiredMode = Schema.RequiredMode.REQUIRED)
    String description;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние отправления", example = "PACKAGE_PREPARED", implementation = TrackingStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    TrackingStatus status;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Текущий город", requiredMode = Schema.RequiredMode.REQUIRED)
    String city;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Время изменения", example = "2025-09-25T01:11:36Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    Instant eventTime;
}

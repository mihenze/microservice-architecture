package com.mihenze.mscurse.deliveryservice.rest.trackingevent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.deliveryservice.entity.TrackingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("CreateTrackingEventRequest")
@Schema(description = "Создать новое отслеживание")
public class CreateTrackingEventRequest {
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
}

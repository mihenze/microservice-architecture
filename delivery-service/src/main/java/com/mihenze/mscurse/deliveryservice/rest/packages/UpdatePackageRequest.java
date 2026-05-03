package com.mihenze.mscurse.deliveryservice.rest.packages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.dtocommon.rest.enums.PackageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("UpdatePackageRequest")
@Schema(description = "Обновить посылку")
public class UpdatePackageRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор посылки", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние посылки", example = "CREATED", implementation = PackageStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    PackageStatus status;
}

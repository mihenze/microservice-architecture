package com.mihenze.mscurse.deliveryservice.rest.packages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("CreatePackageRequest")
@Schema(description = "Создать новую посылку")
public class CreatePackageRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Длина посылки", requiredMode = Schema.RequiredMode.REQUIRED)
    Double length;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Ширина посылки", requiredMode = Schema.RequiredMode.REQUIRED)
    Double width;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Высота посылки", requiredMode = Schema.RequiredMode.REQUIRED)
    Double height;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Единицы измерения габаритов", requiredMode = Schema.RequiredMode.REQUIRED)
    String dimensionUnit;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Вес посылки", requiredMode = Schema.RequiredMode.REQUIRED)
    Double weightValue;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Единицы измерения веса", requiredMode = Schema.RequiredMode.REQUIRED)
    String weightUnit;
}

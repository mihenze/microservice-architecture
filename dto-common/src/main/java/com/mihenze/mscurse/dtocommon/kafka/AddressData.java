package com.mihenze.mscurse.dtocommon.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Данные адресата")
public class AddressData {
    @Schema(description = "Наименование территориального региона доставки", requiredMode = Schema.RequiredMode.REQUIRED)
    String region;

    @Schema(description = "Наименование города", requiredMode = Schema.RequiredMode.REQUIRED)
    String city;

    @Schema(description = "Наименование улицы", requiredMode = Schema.RequiredMode.REQUIRED)
    String street;

    @Schema(description = "Почтовый индекс", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer postcode;

    @Schema(description = "Номер дома", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer building;

    @Schema(description = "Номер квартиры", requiredMode = Schema.RequiredMode.REQUIRED, nullable = true)
    Integer apartment;
}

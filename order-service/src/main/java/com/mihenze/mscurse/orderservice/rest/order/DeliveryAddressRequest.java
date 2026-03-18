package com.mihenze.mscurse.orderservice.rest.order;

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
@JsonTypeName("DeliveryAddressRequest")
@Schema(description = "Адрес доставки заказа")
public class DeliveryAddressRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование территориального региона доставки", requiredMode = Schema.RequiredMode.REQUIRED)
    String region;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование города", requiredMode = Schema.RequiredMode.REQUIRED)
    String city;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование улицы", requiredMode = Schema.RequiredMode.REQUIRED)
    String street;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Номер дома", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer building;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Номер квартиры", requiredMode = Schema.RequiredMode.REQUIRED, nullable = true)
    Integer apartment;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Почтовый индекс", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer postcode;
}

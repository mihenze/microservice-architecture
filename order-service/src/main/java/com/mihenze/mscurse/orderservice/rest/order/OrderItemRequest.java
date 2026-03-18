package com.mihenze.mscurse.orderservice.rest.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("OrderItemRequest")
@Schema(description = "Элемент из заказа")
public class OrderItemRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Наименование элемента заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    String name;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Количество элементов в заказе", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer count;
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Стоимость элемента заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal cost;
}

package com.mihenze.mscurse.orderservice.rest.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeName("CreateOrderRequest")
@Schema(description = "Запрос на создание нового заказа")
public class CreateOrderRequest {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Стоимость всего заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal cost;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Список элементов, входящих в заказ", requiredMode = Schema.RequiredMode.REQUIRED)
    List<OrderItemRequest> items;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Адрес доставки заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    DeliveryAddressRequest address;
}

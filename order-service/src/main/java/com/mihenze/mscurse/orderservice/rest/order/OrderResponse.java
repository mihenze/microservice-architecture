package com.mihenze.mscurse.orderservice.rest.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mihenze.mscurse.orderservice.entity.OrderStatus;
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
@JsonTypeName("OrderResponse")
@Schema(description = "Текущий заказ")
public class OrderResponse {
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Уникальный идентификатор заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Присвоенный идентификатор заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    String uid;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Состояние заказа", example = "CREATED", implementation = OrderStatus.class,
            requiredMode = Schema.RequiredMode.REQUIRED)
    OrderStatus status;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Стоимость всего заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal cost;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Список элементов, входящих в заказ", requiredMode = Schema.RequiredMode.REQUIRED)
    List<OrderItemResponse> items;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @Schema(description = "Адрес доставки заказа", requiredMode = Schema.RequiredMode.REQUIRED)
    DeliveryAddressResponse address;
}

package com.mihenze.mscurse.deliveryservice.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShipmentErrorResponse {
    @Schema(description = "Описание ошибки", example = "Заказ не найден")
    private String message;
    @Schema(description = "Пояснения о причинах ошибки")
    private String description;
}

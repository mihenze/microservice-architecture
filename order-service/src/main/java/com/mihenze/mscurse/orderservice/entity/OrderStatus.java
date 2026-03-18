package com.mihenze.mscurse.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Состояние заказа", enumAsRef = true, name = "OrderStatusEnum")
public enum OrderStatus {
    CREATED, CANCELED, PROCESSED, COLLECTED, DELIVERED, COMPLETED
}

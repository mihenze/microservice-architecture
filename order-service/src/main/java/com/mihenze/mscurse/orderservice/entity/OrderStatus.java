package com.mihenze.mscurse.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Состояние заказа", enumAsRef = true, name = "OrderStatusEnum")
public enum OrderStatus {
    CREATED,
    PAYMENT_AWAITING,
    PAID,
    NOT_PAID,
    CANCELED,
    DELIVERY_AWAITING,
    REFUND_PAID,
    COMPLETED
}

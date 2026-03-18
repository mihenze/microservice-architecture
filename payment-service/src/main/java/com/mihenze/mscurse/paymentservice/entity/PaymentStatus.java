package com.mihenze.mscurse.paymentservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус оплаты", enumAsRef = true, name = "PaymentStatusEnum")
public enum PaymentStatus {
    PENDING, AUTHORIZED, CAPTURED, FAILED, CANCELLED, REFUNDED
}

package com.mihenze.mscurse.paymentservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип оплаты", enumAsRef = true, name = "PaymentTypeEnum")
public enum PaymentType {
    CARD, CASH
}

package com.mihenze.mscurse.paymentservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип транзакции", enumAsRef = true, name = "TransactionalTypeEnum")
public enum TransactionalType {
    AUTHORIZATION, CAPTURE, REFUND , CANCEL
}

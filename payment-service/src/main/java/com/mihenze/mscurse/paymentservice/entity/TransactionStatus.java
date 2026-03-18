package com.mihenze.mscurse.paymentservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус транзакции", enumAsRef = true, name = "TransactionStatusEnum")
public enum TransactionStatus {
    PENDING, SUCCESS, FAILED
}

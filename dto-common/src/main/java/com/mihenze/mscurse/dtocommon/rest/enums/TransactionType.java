package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип транзакции", enumAsRef = true, name = "TransactionalTypeEnum")
public enum TransactionType {
    AUTHORIZATION, CAPTURE, REFUND , CANCEL
}

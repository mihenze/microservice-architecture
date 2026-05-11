package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Статус оплаты", enumAsRef = true, name = "PaymentStatusEnum")
public enum PaymentStatus {
    PENDING, CONFIRM, FAILED, REFUNDED
}

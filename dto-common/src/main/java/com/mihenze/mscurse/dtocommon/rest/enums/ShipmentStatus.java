package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Состояние отправления", enumAsRef = true, name = "ShipmentStatusEnum")
public enum ShipmentStatus {
    CREATED,
    PROCESSING,
    SHIPPED,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED,
    RETURNED,
}

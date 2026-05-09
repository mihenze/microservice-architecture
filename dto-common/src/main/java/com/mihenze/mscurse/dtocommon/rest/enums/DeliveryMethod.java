package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Метод доставки", enumAsRef = true, name = "DeliveryMethodEnum")
public enum DeliveryMethod {
    COURIER,
    POST,
    PICKUP_POINT,
    LOCKER
}

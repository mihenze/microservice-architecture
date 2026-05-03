package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Состояние упаковки", enumAsRef = true, name = "PackageStatusEnum")
public enum PackageStatus {
    CREATED,
    PACKED,
    SHIPPED,
    DELIVERED
}

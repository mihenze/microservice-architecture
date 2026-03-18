package com.mihenze.mscurse.deliveryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Состояние отслеживания", enumAsRef = true, name = "TrackingStatusEnum")
public enum TrackingStatus {
    PACKAGE_PREPARED,
    PICKED_UP,
    DEPARTED_FROM_WAREHOUSE,
    ARRIVED_AT_HUB,
    SORTED,
    DEPARTED_FROM_HUB,
    ARRIVED_AT_DESTINATION_CITY,
    COURIER_ASSIGNED,
    OUT_FOR_DELIVERY,
    DELIVERED
}

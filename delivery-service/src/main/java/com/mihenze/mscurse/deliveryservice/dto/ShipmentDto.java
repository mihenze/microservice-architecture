package com.mihenze.mscurse.deliveryservice.dto;

import com.mihenze.mscurse.deliveryservice.entity.DeliveryMethod;
import com.mihenze.mscurse.deliveryservice.entity.ShipmentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentDto {
    Long id;
    Long orderId;
    String addressRegion;
    String addressCity;
    String addressStreet;
    Integer addressPostcode;
    Integer addressBuilding;
    Integer addressApartment;
    DeliveryMethod deliveryMethod;
    ShipmentStatus status;
    String trackingNumber;
    Instant createdAt;
    Instant updatedAt;
    List<PackageDto> packages;
    List<TrackingEventDto> events;
}

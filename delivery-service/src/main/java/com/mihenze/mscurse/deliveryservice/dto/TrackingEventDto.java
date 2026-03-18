package com.mihenze.mscurse.deliveryservice.dto;

import com.mihenze.mscurse.deliveryservice.entity.TrackingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackingEventDto {
    Long id;
    String description;
    TrackingStatus status;
    String city;
    Instant eventTime;
}

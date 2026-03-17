package com.mihenze.mscurse.orderservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryAddressDto {
    Long id;
    String region;
    String city;
    String street;
    Integer building;
    Integer apartment;
    Integer postcode;
}

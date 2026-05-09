package com.mihenze.mscurse.deliveryservice.dto;

import com.mihenze.mscurse.dtocommon.rest.enums.PackageStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageDto {
    Long id;
    Double length;
    Double width;
    Double height;
    String dimensionUnit;
    Double weightValue;
    String weightUnit;
    PackageStatus status;
}

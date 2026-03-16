package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.PackageDto;
import com.mihenze.mscurse.deliveryservice.entity.Package;
import com.mihenze.mscurse.deliveryservice.rest.shipment.PackageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    Package mapToPackage(PackageDto packageDto);

    PackageDto mapToPackageDto(Package p);

    PackageResponse mapToPackageResponse(PackageDto packageDto);
}

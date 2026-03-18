package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.PackageDto;
import com.mihenze.mscurse.deliveryservice.entity.Package;
import com.mihenze.mscurse.deliveryservice.rest.packages.CreatePackageRequest;
import com.mihenze.mscurse.deliveryservice.rest.packages.PackageResponse;
import com.mihenze.mscurse.deliveryservice.rest.packages.UpdatePackageRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    Package mapToPackage(PackageDto packageDto);

    PackageDto mapToPackageDto(Package p);

    PackageDto mapToPackageDto(CreatePackageRequest packageRequest);

    PackageDto mapToPackageDto(UpdatePackageRequest packageRequest);

    PackageResponse mapToPackageResponse(PackageDto packageDto);
}

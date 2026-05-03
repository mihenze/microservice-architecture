package com.mihenze.mscurse.deliveryservice.service;

import com.mihenze.mscurse.deliveryservice.dto.PackageDto;
import com.mihenze.mscurse.deliveryservice.entity.Package;
import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundPackageException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundShipmentException;
import com.mihenze.mscurse.deliveryservice.mapper.PackageMapper;
import com.mihenze.mscurse.deliveryservice.repository.PackageRepository;
import com.mihenze.mscurse.deliveryservice.repository.ShipmentRepository;
import com.mihenze.mscurse.dtocommon.rest.enums.PackageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PackageService {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;
    private final ShipmentRepository shipmentRepository;

    public PackageDto getPackageById(Long id) {
        Package pack = packageRepository.findById(id)
                .orElseThrow(() -> new NotFoundPackageException(id));

        return packageMapper.mapToPackageDto(pack);

    }

    @Transactional
    public PackageDto createPackage(Long id, PackageDto packageDto) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundShipmentException(id));

        Package newPackage = packageMapper.mapToPackage(packageDto);
        newPackage.setShipment(shipment);
        newPackage.setStatus(PackageStatus.CREATED);

        Package saved = packageRepository.save(newPackage);
        return packageMapper.mapToPackageDto(saved);
    }

    @Transactional
    public PackageDto updatePackage(PackageDto packageDto) {
        Package newPackage = packageMapper.mapToPackage(packageDto);

        Package pack = packageRepository.findById(newPackage.getId())
                .orElseThrow(() -> new NotFoundPackageException(newPackage.getId()));

        pack.setStatus(newPackage.getStatus());

        Package saved = packageRepository.save(pack);
        return packageMapper.mapToPackageDto(saved);
    }

    @Transactional
    public void deletePackage(Long id) {
        if (!packageRepository.existsById(id)) {
            throw new NotFoundPackageException(id);
        }

        packageRepository.deleteById(id);
    }
}

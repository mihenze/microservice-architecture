package com.mihenze.mscurse.deliveryservice.controller;

import com.mihenze.mscurse.deliveryservice.controller.doc.PackageControllerDoc;
import com.mihenze.mscurse.deliveryservice.mapper.PackageMapper;
import com.mihenze.mscurse.deliveryservice.rest.packages.CreatePackageRequest;
import com.mihenze.mscurse.deliveryservice.rest.packages.UpdatePackageRequest;
import com.mihenze.mscurse.deliveryservice.service.PackageService;
import com.mihenze.mscurse.dtocommon.rest.shipment.packages.PackageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class PackageController implements PackageControllerDoc {
    private final PackageService packageService;
    private final PackageMapper packageMapper;

    @GetMapping("/packages/{package_id}")
    @Override
    public ResponseEntity<PackageResponse> getPackage(@PathVariable("package_id") Long id) {
        return ResponseEntity.ok(packageMapper.mapToPackageResponse(packageService.getPackageById(id)));
    }

    @PostMapping("/{shipment_id}/packages")
    @Override
    public ResponseEntity<PackageResponse> createPackage(@PathVariable("shipment_id") Long shipmentId,
                                                         @RequestBody CreatePackageRequest request) {
        return ResponseEntity.ok(packageMapper.mapToPackageResponse(packageService
                .createPackage(shipmentId, packageMapper.mapToPackageDto(request))));
    }

    @PatchMapping("/packages")
    @Override
    public ResponseEntity<PackageResponse> updatePackage(@RequestBody UpdatePackageRequest request) {
        return ResponseEntity.ok(packageMapper.mapToPackageResponse(packageService
                .updatePackage(packageMapper.mapToPackageDto(request))));
    }

    @DeleteMapping("/packages/{package_id}")
    @Override
    public ResponseEntity<Void> deletePackage(@PathVariable("package_id") Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok().build();
    }
}

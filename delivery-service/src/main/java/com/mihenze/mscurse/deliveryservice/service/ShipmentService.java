package com.mihenze.mscurse.deliveryservice.service;

import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.entity.Package;
import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import com.mihenze.mscurse.deliveryservice.entity.ShipmentStatus;
import com.mihenze.mscurse.deliveryservice.entity.TrackingEvent;
import com.mihenze.mscurse.deliveryservice.exception.InvalidUpdateShipmentException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundShipmentException;
import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.deliveryservice.repository.PackageRepository;
import com.mihenze.mscurse.deliveryservice.repository.ShipmentRepository;
import com.mihenze.mscurse.deliveryservice.repository.TrackingEventRepository;
import com.mihenze.mscurse.deliveryservice.util.TrackingNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final PackageRepository packageRepository;
    private final TrackingEventRepository trackingEventRepository;
    private final ShipmentMapper shipmentMapper;
    private final TrackingNumberGenerator trackingNumberGenerator;

    public ShipmentDto getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundShipmentException(id));

        List<Package> packages = packageRepository.findAllById(List.of(shipment.getId()));
        List<TrackingEvent> events = trackingEventRepository.findAllById(List.of(shipment.getId()));

        shipment.setPackages(packages);
        shipment.setEvents(events);

        return shipmentMapper.mapToShipmentDto(shipment);
    }

    @Transactional
    public ShipmentDto createShipment(ShipmentDto shipment) {
        Shipment newShipment = shipmentMapper.mapToShipment(shipment);

        newShipment.setStatus(ShipmentStatus.CREATED);
        newShipment.setTrackingNumber(trackingNumberGenerator.generate());

        Shipment saved = shipmentRepository.save(newShipment);
        return shipmentMapper.mapToShipmentDto(saved);
    }

    @Transactional
    public ShipmentDto updateShipment(ShipmentDto shipment) {
        Shipment newShipment = shipmentMapper.mapToShipment(shipment);

        Shipment shipmentOld = shipmentRepository.findById(newShipment.getId())
                .orElseThrow(() -> new NotFoundShipmentException(newShipment.getId()));

        if (shipmentOld.getStatus() != ShipmentStatus.CREATED &&
                shipmentOld.getStatus() != ShipmentStatus.PROCESSING) {
            throw new InvalidUpdateShipmentException();
        }

        shipmentOld.setAddressRegion(newShipment.getAddressRegion());
        shipmentOld.setAddressCity(newShipment.getAddressCity());
        shipmentOld.setAddressStreet(newShipment.getAddressStreet());
        shipmentOld.setAddressPostcode(newShipment.getAddressPostcode());
        shipmentOld.setAddressBuilding(newShipment.getAddressBuilding());
        shipmentOld.setAddressApartment(newShipment.getAddressApartment());
        shipmentOld.setDeliveryMethod(newShipment.getDeliveryMethod());

        Shipment saved = shipmentRepository.save(shipmentOld);
        return shipmentMapper.mapToShipmentDto(saved);
    }

    @Transactional
    public void deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) {
            throw new NotFoundShipmentException(id);
        }

        shipmentRepository.deleteById(id);
    }
}

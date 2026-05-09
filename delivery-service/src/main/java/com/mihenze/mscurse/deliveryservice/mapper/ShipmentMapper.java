package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import com.mihenze.mscurse.deliveryservice.rest.shipment.UpdateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TrackingEventMapper.class, PackageMapper.class})
public interface ShipmentMapper {
    Shipment mapToShipment(ShipmentDto shipment);

    ShipmentDto mapToShipmentDto(Shipment shipment);

    ShipmentDto mapToShipmentDto(CreateShipmentRequest shipment);

    ShipmentDto mapToShipmentDto(UpdateShipmentRequest shipment);

    ShipmentResponse mapToShipmentResponse(ShipmentDto shipment);
}

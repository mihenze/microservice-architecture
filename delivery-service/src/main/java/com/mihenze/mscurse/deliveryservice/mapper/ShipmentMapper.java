package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import com.mihenze.mscurse.deliveryservice.rest.shipment.UpdateShipmentRequest;
import com.mihenze.mscurse.dtocommon.kafka.AddressData;
import com.mihenze.mscurse.dtocommon.kafka.OrderCreationStatusMessage;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TrackingEventMapper.class, PackageMapper.class})
public interface ShipmentMapper {
    Shipment mapToShipment(ShipmentDto shipment);

    ShipmentDto mapToShipmentDto(Shipment shipment);

    ShipmentDto mapToShipmentDto(CreateShipmentRequest shipment);

    ShipmentDto mapToShipmentDto(UpdateShipmentRequest shipment);

    @Mapping(target = "addressRegion", source = "message.addressData.region")
    @Mapping(target = "addressCity", source = "message.addressData.city")
    @Mapping(target = "addressStreet", source = "message.addressData.street")
    @Mapping(target = "addressPostcode", source = "message.addressData.postcode")
    @Mapping(target = "addressBuilding", source = "message.addressData.building")
    @Mapping(target = "addressApartment", source = "message.addressData.apartment")
    ShipmentDto mapToShipmentDto(OrderCreationStatusMessage message);

    ShipmentResponse mapToShipmentResponse(ShipmentDto shipment);

    @Mapping(target = "region", source = "shipment.addressRegion")
    @Mapping(target = "city", source = "shipment.addressCity")
    @Mapping(target = "street", source = "shipment.addressStreet")
    @Mapping(target = "postcode", source = "shipment.addressPostcode")
    @Mapping(target = "building", source = "shipment.addressBuilding")
    @Mapping(target = "apartment", source = "shipment.addressApartment")
    AddressData mapToAddressData(ShipmentDto shipment);
}

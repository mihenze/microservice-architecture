package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.dto.DeliveryAddressDto;
import com.mihenze.mscurse.orderservice.entity.DeliveryAddress;
import com.mihenze.mscurse.orderservice.rest.order.DeliveryAddressRequest;
import com.mihenze.mscurse.orderservice.rest.order.DeliveryAddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper {
    DeliveryAddressResponse mapToDeliveryAddressResponse(DeliveryAddressDto address);

    DeliveryAddressDto mapToDeliveryAddressDto(DeliveryAddress deliveryAddress);

    DeliveryAddressDto mapToDeliveryAddressDto(DeliveryAddressRequest deliveryAddress);

    DeliveryAddress mapToDeliveryAddress(DeliveryAddressDto deliveryAddress);
}

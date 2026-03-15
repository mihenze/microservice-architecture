package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.entity.DeliveryAddress;
import com.mihenze.mscurse.orderservice.rest.order.DeliveryAddressRequest;
import com.mihenze.mscurse.orderservice.rest.order.DeliveryAddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper {
    DeliveryAddressResponse mapToDeliveryAddressResponse(DeliveryAddress address);

    DeliveryAddress mapToDeliveryAddress(DeliveryAddressRequest deliveryAddress);
}

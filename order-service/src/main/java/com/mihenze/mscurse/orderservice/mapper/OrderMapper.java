package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.entity.Order;
import com.mihenze.mscurse.orderservice.rest.order.CreateOrderRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderResponse;
import com.mihenze.mscurse.orderservice.rest.order.UpdateOrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, DeliveryAddressMapper.class})
public interface OrderMapper {
    OrderResponse mapToOrderResponse(Order order);

    Order mapToOrder(CreateOrderRequest order);
    Order mapToOrder(UpdateOrderRequest order);
}

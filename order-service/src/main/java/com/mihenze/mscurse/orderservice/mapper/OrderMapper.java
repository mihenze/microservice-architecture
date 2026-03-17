package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.dto.OrderDto;
import com.mihenze.mscurse.orderservice.entity.Order;
import com.mihenze.mscurse.orderservice.rest.order.CreateOrderRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderResponse;
import com.mihenze.mscurse.orderservice.rest.order.UpdateOrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, DeliveryAddressMapper.class})
public interface OrderMapper {
    OrderResponse mapToOrderResponse(OrderDto order);

    OrderDto mapToOrderDto(Order order);

    OrderDto mapToOrderDto(CreateOrderRequest order);

    OrderDto mapToOrderDto(UpdateOrderRequest order);

    Order mapToOrder(OrderDto order);
}

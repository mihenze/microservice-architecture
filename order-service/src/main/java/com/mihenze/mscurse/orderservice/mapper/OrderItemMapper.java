package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.entity.OrderItem;
import com.mihenze.mscurse.orderservice.rest.order.OrderItemRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse mapToOrderItemResponse(OrderItem orderItem);

    OrderItem mapToOrderItem(OrderItemRequest orderItem);
}

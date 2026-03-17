package com.mihenze.mscurse.orderservice.mapper;

import com.mihenze.mscurse.orderservice.dto.OrderItemDto;
import com.mihenze.mscurse.orderservice.entity.OrderItem;
import com.mihenze.mscurse.orderservice.rest.order.OrderItemRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse mapToOrderItemResponse(OrderItemDto orderItem);

    OrderItemDto mapToOrderItemDto(OrderItem orderItem);

    OrderItemDto mapToOrderItemDto(OrderItemRequest orderItem);

    OrderItem mapToOrderItem(OrderItemDto orderItem);
}

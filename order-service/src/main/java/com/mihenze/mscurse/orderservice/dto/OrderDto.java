package com.mihenze.mscurse.orderservice.dto;

import com.mihenze.mscurse.orderservice.entity.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long id;
    String uid;
    OrderStatus status;
    BigDecimal cost;
    List<OrderItemDto> items;
    DeliveryAddressDto address;
}

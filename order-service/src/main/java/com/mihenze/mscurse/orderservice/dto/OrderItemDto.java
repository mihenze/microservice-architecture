package com.mihenze.mscurse.orderservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDto {
    Long id;
    String name;
    Integer count;
    BigDecimal cost;
}

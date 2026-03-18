package com.mihenze.mscurse.orderservice.exception;

import com.mihenze.mscurse.orderservice.entity.OrderStatus;

public class InvalidUpdateOrderException extends RuntimeException {
    public InvalidUpdateOrderException(OrderStatus status) {
        super("It is not possible to change the order, order status = %s (to change the status must be %s)".formatted(status, OrderStatus.CREATED));
    }
}

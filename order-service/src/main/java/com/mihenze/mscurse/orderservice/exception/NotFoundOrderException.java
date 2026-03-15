package com.mihenze.mscurse.orderservice.exception;

public class NotFoundOrderException extends RuntimeException {
    public NotFoundOrderException(Long id) {
        super("Order not found with id: %s".formatted(id));
    }
}

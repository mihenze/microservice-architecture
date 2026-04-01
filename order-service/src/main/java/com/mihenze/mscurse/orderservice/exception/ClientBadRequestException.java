package com.mihenze.mscurse.orderservice.exception;

public class ClientBadRequestException extends RuntimeException {
    public ClientBadRequestException(String message) {
        super(message);
    }
}

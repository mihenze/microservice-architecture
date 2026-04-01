package com.mihenze.mscurse.orderservice.exception;

public class ClientConflictException extends RuntimeException {
    public ClientConflictException(String message) {
        super(message);
    }
}

package com.mihenze.mscurse.deliveryservice.exception;

public class NotFoundShipmentException extends RuntimeException {
    public NotFoundShipmentException(Long id) {
        super("Shipment not found with id: %s".formatted(id));
    }
}

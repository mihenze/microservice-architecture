package com.mihenze.mscurse.deliveryservice.exception;

public class InvalidUpdateShipmentException extends RuntimeException {
    public InvalidUpdateShipmentException() {
        super("It is not possible to change the shipment");
    }
}

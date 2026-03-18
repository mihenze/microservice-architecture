package com.mihenze.mscurse.paymentservice.exception;

public class InvalidUpdatePaymentException extends RuntimeException {
    public InvalidUpdatePaymentException() {
        super("It is not possible to change the payment because there are transactions");
    }
}

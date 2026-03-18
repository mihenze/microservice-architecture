package com.mihenze.mscurse.paymentservice.exception;

public class NotFoundPaymentException extends RuntimeException {
    public NotFoundPaymentException(Long id) {
        super("Payment not found with id: %s".formatted(id));
    }
}

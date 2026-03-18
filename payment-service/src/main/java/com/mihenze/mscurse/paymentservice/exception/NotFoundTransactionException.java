package com.mihenze.mscurse.paymentservice.exception;

public class NotFoundTransactionException extends RuntimeException {
    public NotFoundTransactionException(Long id) {
        super("Transaction not found with id: %s".formatted(id));
    }
}

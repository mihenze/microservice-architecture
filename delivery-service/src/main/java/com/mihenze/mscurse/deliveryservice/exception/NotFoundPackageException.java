package com.mihenze.mscurse.deliveryservice.exception;

public class NotFoundPackageException extends RuntimeException {
    public NotFoundPackageException(Long id) {
        super("Package not found with id: %s".formatted(id));
    }
}

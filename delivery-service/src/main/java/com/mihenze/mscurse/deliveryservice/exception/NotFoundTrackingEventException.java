package com.mihenze.mscurse.deliveryservice.exception;

public class NotFoundTrackingEventException extends RuntimeException {
    public NotFoundTrackingEventException(Long id) {
        super("TrackingEvent not found with id: %s".formatted(id));
    }
}

package com.mihenze.mscurse.deliveryservice.rest;

import com.mihenze.mscurse.deliveryservice.exception.InvalidUpdateShipmentException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundPackageException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundShipmentException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundTrackingEventException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundShipmentException.class)
    public ResponseEntity<DeliveryErrorResponse> handleNotFoundPaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        DeliveryErrorResponse errorResponse = new DeliveryErrorResponse("Отправление не найдено", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundPackageException.class)
    public ResponseEntity<DeliveryErrorResponse> handleNotFoundPackageException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        DeliveryErrorResponse errorResponse = new DeliveryErrorResponse("Посылка не найдена", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundTrackingEventException.class)
    public ResponseEntity<DeliveryErrorResponse> handleNotFoundTrackingEventException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        DeliveryErrorResponse errorResponse = new DeliveryErrorResponse("Отслеживание не найдено", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUpdateShipmentException.class)
    public ResponseEntity<DeliveryErrorResponse> handleInvalidUpdatePaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        DeliveryErrorResponse errorResponse = new DeliveryErrorResponse("Невозможно обновить отправление", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DeliveryErrorResponse> handleUnknownException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        DeliveryErrorResponse errorResponse = new DeliveryErrorResponse("Незадекларированная ошибка сервера", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

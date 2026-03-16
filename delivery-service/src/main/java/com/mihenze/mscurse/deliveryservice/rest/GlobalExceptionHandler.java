package com.mihenze.mscurse.deliveryservice.rest;

import com.mihenze.mscurse.deliveryservice.exception.InvalidUpdateShipmentException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundShipmentException;
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
    public ResponseEntity<ShipmentErrorResponse> handleNotFoundPaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        ShipmentErrorResponse errorResponse = new ShipmentErrorResponse("Отправление не найдено", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUpdateShipmentException.class)
    public ResponseEntity<ShipmentErrorResponse> handleInvalidUpdatePaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        ShipmentErrorResponse errorResponse = new ShipmentErrorResponse("Невозможно обновить отправление", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ShipmentErrorResponse> handleUnknownException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        ShipmentErrorResponse errorResponse = new ShipmentErrorResponse("Незадекларированная ошибка сервера", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

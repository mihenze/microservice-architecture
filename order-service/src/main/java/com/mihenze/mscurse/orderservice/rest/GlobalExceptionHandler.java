package com.mihenze.mscurse.orderservice.rest;

import com.mihenze.mscurse.orderservice.exception.InvalidUpdateOrderException;
import com.mihenze.mscurse.orderservice.exception.NotFoundOrderException;
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
    @ExceptionHandler({NotFoundOrderException.class})
    public ResponseEntity<OrderErrorResponse> handleNotFoundOrderException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        OrderErrorResponse errorResponse = new OrderErrorResponse("Заказ не найден", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidUpdateOrderException.class})
    public ResponseEntity<OrderErrorResponse> handleInvalidUpdateOrderException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        OrderErrorResponse errorResponse = new OrderErrorResponse("Невозможно обновить заказ", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrderErrorResponse> handleUnknownException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        OrderErrorResponse errorResponse = new OrderErrorResponse("Незадекларированная ошибка сервера", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

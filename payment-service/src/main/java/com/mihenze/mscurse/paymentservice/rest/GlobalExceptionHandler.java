package com.mihenze.mscurse.paymentservice.rest;

import com.mihenze.mscurse.paymentservice.exception.InvalidUpdatePaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundPaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundTransactionException;
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
    @ExceptionHandler(NotFoundPaymentException.class)
    public ResponseEntity<PaymentErrorResponse> handleNotFoundPaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        PaymentErrorResponse errorResponse = new PaymentErrorResponse("Оплата не найдена", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundTransactionException.class)
    public ResponseEntity<PaymentErrorResponse> handleNotFoundTransactionalException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        PaymentErrorResponse errorResponse = new PaymentErrorResponse("Транзакция не найдена", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidUpdatePaymentException.class)
    public ResponseEntity<PaymentErrorResponse> handleInvalidUpdatePaymentException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        PaymentErrorResponse errorResponse = new PaymentErrorResponse("Невозможно обновить оплату", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaymentErrorResponse> handleUnknownException(Exception exception) {
        log.error("Date:%s\nstacktrace: STACK BEGIN\n%s\nSTACK END."
                .formatted(Instant.now(), getStringStackTrace(exception)));
        PaymentErrorResponse errorResponse = new PaymentErrorResponse("Незадекларированная ошибка сервера", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStringStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

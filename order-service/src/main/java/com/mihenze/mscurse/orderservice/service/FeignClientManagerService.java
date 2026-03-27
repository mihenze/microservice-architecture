package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
import com.mihenze.mscurse.orderservice.feign.FeignExceptionHandled;
import com.mihenze.mscurse.orderservice.feign.PaymentServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignClientManagerService {
    private final PaymentServiceClient paymentServiceClient;

    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name = "paymentServiceCircuitBreaker")
    @FeignExceptionHandled
    public void createPayment(Long orderId, BigDecimal amount) {
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .type(PaymentType.CARD)
                .amount(amount)
                .currency(Currency.RUB)
                .orderId(orderId)
                .build();

        paymentServiceClient.createPayment(UUID.randomUUID().toString(),
                createPaymentRequest);
    }
}

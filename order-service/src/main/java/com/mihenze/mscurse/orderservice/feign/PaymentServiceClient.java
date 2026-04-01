package com.mihenze.mscurse.orderservice.feign;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service", url = "${integration.payment-service.base-url}")
public interface PaymentServiceClient {

    @PostMapping
    ResponseEntity <PaymentResponse> createPayment(@RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                                   @RequestBody CreatePaymentRequest request);
}

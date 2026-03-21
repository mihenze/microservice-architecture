package com.mihenze.mscurse.orderservice.feign;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8002/api/v1/payments")
public interface PaymentServiceClient {

    @PostMapping
    ResponseEntity <PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request);
}

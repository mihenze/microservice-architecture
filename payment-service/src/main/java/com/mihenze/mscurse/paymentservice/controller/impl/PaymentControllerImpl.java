package com.mihenze.mscurse.paymentservice.controller.impl;

import com.mihenze.mscurse.paymentservice.controller.PaymentController;
import com.mihenze.mscurse.paymentservice.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.payment.PaymentResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.UpdatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.CreateTransactionalRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.TransactionalResponse;
import com.mihenze.mscurse.paymentservice.rest.transaction.UpdateTransactionalRequest;
import com.mihenze.mscurse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{payment_id}")
    @Override
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("payment_id") Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @PutMapping
    @Override
    public ResponseEntity<PaymentResponse> updatePayment(@RequestBody UpdatePaymentRequest request) {
        return ResponseEntity.ok(paymentService.updatePayment(request));
    }

    @DeleteMapping("/{payment_id}")
    @Override
    public ResponseEntity<Void> deletePayment(@PathVariable("payment_id") Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{payment_id}/transactions")
    @Override
    public ResponseEntity<TransactionalResponse> createTransactional(@PathVariable("payment_id") Long id,
                                                                     @RequestBody CreateTransactionalRequest request) {
        return ResponseEntity.ok(paymentService.createTransaction(id, request));
    }

    @PatchMapping("/{payment_id}/transactions")
    @Override
    public ResponseEntity<TransactionalResponse> updateTransactional(@PathVariable("payment_id") Long id,
                                                                     @RequestBody UpdateTransactionalRequest request) {
        return ResponseEntity.ok(paymentService.updateTransaction(id, request));
    }

    @DeleteMapping("/transactions/{transaction_id}")
    @Override
    public ResponseEntity<Void> deleteTransactional(@PathVariable("transaction_id") Long id) {
        paymentService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}

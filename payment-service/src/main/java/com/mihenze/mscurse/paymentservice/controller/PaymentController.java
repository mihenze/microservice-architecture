package com.mihenze.mscurse.paymentservice.controller;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import com.mihenze.mscurse.dtocommon.rest.payment.UpdatePaymentRequest;
import com.mihenze.mscurse.paymentservice.aop.Idempotent;
import com.mihenze.mscurse.paymentservice.controller.doc.PaymentControllerDoc;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import com.mihenze.mscurse.paymentservice.mapper.TransactionMapper;
import com.mihenze.mscurse.dtocommon.rest.transaction.CreateTransactionRequest;
import com.mihenze.mscurse.dtocommon.rest.transaction.TransactionResponse;
import com.mihenze.mscurse.dtocommon.rest.transaction.UpdateTransactionRequest;
import com.mihenze.mscurse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController implements PaymentControllerDoc {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final TransactionMapper transactionMapper;

    @GetMapping("/{payment_id}")
    @Override
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("payment_id") Long id) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentResponse(paymentService.getPaymentById(id)));
    }

    @Idempotent
    @PostMapping
    @Override
    public ResponseEntity<PaymentResponse> createPayment(@RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                                         @RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentResponse(paymentService
                .createPayment(paymentMapper.mapToPaymentDto(request))));
    }

    @PutMapping
    @Override
    public ResponseEntity<PaymentResponse> updatePayment(@RequestBody UpdatePaymentRequest request) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentResponse(paymentService
                .updatePayment(paymentMapper.mapToPaymentDto(request))));
    }

    @DeleteMapping("/{payment_id}")
    @Override
    public ResponseEntity<Void> deletePayment(@PathVariable("payment_id") Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{payment_id}/transactions")
    @Override
    public ResponseEntity<TransactionResponse> createTransactional(@PathVariable("payment_id") Long id,
                                                                   @RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok(transactionMapper.mapToTransactionResponse(paymentService
                .createTransaction(id, transactionMapper.mapToTransactionDto(request))));
    }

    @PatchMapping("/{payment_id}/transactions")
    @Override
    public ResponseEntity<TransactionResponse> updateTransactional(@PathVariable("payment_id") Long id,
                                                                   @RequestBody UpdateTransactionRequest request) {
        return ResponseEntity.ok(transactionMapper.mapToTransactionResponse(paymentService
                .updateTransaction(id, transactionMapper.mapToTransactionDto(request))));
    }

    @DeleteMapping("/transactions/{transaction_id}")
    @Override
    public ResponseEntity<Void> deleteTransactional(@PathVariable("transaction_id") Long id) {
        paymentService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}

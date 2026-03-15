package com.mihenze.mscurse.paymentservice.service;

import com.mihenze.mscurse.paymentservice.entity.*;
import com.mihenze.mscurse.paymentservice.exception.InvalidUpdatePaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundPaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundTransactionException;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import com.mihenze.mscurse.paymentservice.mapper.TransactionalMapper;
import com.mihenze.mscurse.paymentservice.repository.PaymentRepository;
import com.mihenze.mscurse.paymentservice.repository.TransactionalRepository;
import com.mihenze.mscurse.paymentservice.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.payment.PaymentResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.UpdatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.CreateTransactionalRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.TransactionalResponse;
import com.mihenze.mscurse.paymentservice.rest.transaction.UpdateTransactionalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TransactionalRepository transactionalRepository;
    private final PaymentMapper paymentMapper;
    private final TransactionalMapper transactionalMapper;

    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundPaymentException(id));

        return paymentMapper.mapToPaymentResponse(payment);
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {
        Payment payment = paymentMapper.mapToPayment(request);

        payment.setStatus(PaymentStatus.PENDING);

        Payment saved = paymentRepository.save(payment);
        return paymentMapper.mapToPaymentResponse(payment);
    }

    public PaymentResponse updatePayment(UpdatePaymentRequest request) {
        Payment payment = paymentRepository.findByIdFetch(request.getId())
                .orElseThrow(() -> new NotFoundPaymentException(request.getId()));
        if (payment.getTransactions().isEmpty()) {
            Payment upd = paymentMapper.mapToPayment(request);
            payment.setStatus(upd.getStatus());
            payment.setType(upd.getType());
            payment.setAmount(upd.getAmount());
            payment.setCurrency(upd.getCurrency());

            Payment saved = paymentRepository.save(payment);
            return paymentMapper.mapToPaymentResponse(saved);
        } else {
            throw new InvalidUpdatePaymentException();
        }
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NotFoundPaymentException(id);
        }

        paymentRepository.deleteById(id);
    }

    public TransactionalResponse createTransaction(Long id, CreateTransactionalRequest request) {
        Payment payment = paymentRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundPaymentException(id));

        Transaction transaction = transactionalMapper.mapToTransaction(request);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setPayment(payment);

        Transaction saved = transactionalRepository.save(transaction);
        return transactionalMapper.mapToTransactionalResponse(transaction);
    }

    public TransactionalResponse updateTransaction(Long id, UpdateTransactionalRequest request) {
        Transaction transaction = transactionalRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundTransactionException(id));

        Transaction upd = transactionalMapper.mapToTransaction(request);
        transaction.setStatus(upd.getStatus());
        transaction.setType(upd.getType());

        Transaction saved = transactionalRepository.save(transaction);
        return transactionalMapper.mapToTransactionalResponse(saved);
    }

    public void deleteTransaction(Long id) {
        if (!transactionalRepository.existsById(id)) {
            throw new NotFoundTransactionException(id);
        }

        transactionalRepository.deleteById(id);
    }
}

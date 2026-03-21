package com.mihenze.mscurse.paymentservice.service;

import com.mihenze.mscurse.dtocommon.rest.enums.PaymentStatus;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionStatus;
import com.mihenze.mscurse.dtocommon.rest.enums.TransactionType;
import com.mihenze.mscurse.paymentservice.dto.PaymentDto;
import com.mihenze.mscurse.paymentservice.dto.TransactionDto;
import com.mihenze.mscurse.paymentservice.entity.*;
import com.mihenze.mscurse.paymentservice.exception.InvalidUpdatePaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundPaymentException;
import com.mihenze.mscurse.paymentservice.exception.NotFoundTransactionException;
import com.mihenze.mscurse.paymentservice.mapper.PaymentMapper;
import com.mihenze.mscurse.paymentservice.mapper.TransactionMapper;
import com.mihenze.mscurse.paymentservice.repository.PaymentRepository;
import com.mihenze.mscurse.paymentservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;
    private final PaymentMapper paymentMapper;
    private final TransactionMapper transactionMapper;

    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundPaymentException(id));

        return paymentMapper.mapToPaymentDto(payment);
    }

    @Transactional
    public PaymentDto createPayment(PaymentDto request) {
        Payment payment = paymentMapper.mapToPayment(request);
        payment.setStatus(PaymentStatus.PENDING);
        Payment saved = paymentRepository.save(payment);

        // создадим транзакцию
        TransactionDto transactionDto = TransactionDto.builder()
                .type(TransactionType.AUTHORIZATION)
                .amount(saved.getAmount())
                .currency(saved.getCurrency())
                .build();

        Transaction transaction = transactionMapper.mapToTransaction(transactionDto);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setPayment(payment);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return paymentMapper.mapToPaymentDto(saved);
    }

    @Transactional
    public PaymentDto updatePayment(PaymentDto request) {
        Payment payment = paymentRepository.findByIdFetch(request.getId())
                .orElseThrow(() -> new NotFoundPaymentException(request.getId()));
        if (payment.getTransactions().isEmpty()) {
            Payment upd = paymentMapper.mapToPayment(request);
            payment.setStatus(upd.getStatus());
            payment.setType(upd.getType());
            payment.setAmount(upd.getAmount());
            payment.setCurrency(upd.getCurrency());

            Payment saved = paymentRepository.save(payment);
            return paymentMapper.mapToPaymentDto(saved);
        } else {
            throw new InvalidUpdatePaymentException();
        }
    }

    @Transactional
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NotFoundPaymentException(id);
        }

        paymentRepository.deleteById(id);
    }

    @Transactional
    public TransactionDto createTransaction(Long id, TransactionDto request) {
        Payment payment = paymentRepository.findByIdFetch(id)
                .orElseThrow(() -> new NotFoundPaymentException(id));

        Transaction transaction = transactionMapper.mapToTransaction(request);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setPayment(payment);

        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.mapToTransactionDto(transaction);
    }

    @Transactional
    public TransactionDto updateTransaction(Long id, TransactionDto request) {
        Transaction transaction = transactionRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundTransactionException(id));

        Transaction upd = transactionMapper.mapToTransaction(request);
        transaction.setStatus(upd.getStatus());
        transaction.setType(upd.getType());

        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.mapToTransactionDto(saved);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new NotFoundTransactionException(id);
        }

        transactionRepository.deleteById(id);
    }
}

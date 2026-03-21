package com.mihenze.mscurse.paymentservice.mapper;

import com.mihenze.mscurse.paymentservice.dto.TransactionDto;
import com.mihenze.mscurse.paymentservice.entity.Transaction;
import com.mihenze.mscurse.dtocommon.rest.transaction.CreateTransactionRequest;
import com.mihenze.mscurse.dtocommon.rest.transaction.TransactionResponse;
import com.mihenze.mscurse.dtocommon.rest.transaction.UpdateTransactionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse mapToTransactionResponse(TransactionDto transaction);

    Transaction mapToTransaction(TransactionDto transaction);

    TransactionDto mapToTransactionDto(Transaction transaction);

    TransactionDto mapToTransactionDto(CreateTransactionRequest transactional);

    TransactionDto mapToTransactionDto(UpdateTransactionRequest transactional);
}

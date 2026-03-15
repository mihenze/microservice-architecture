package com.mihenze.mscurse.paymentservice.mapper;

import com.mihenze.mscurse.paymentservice.entity.Transaction;
import com.mihenze.mscurse.paymentservice.rest.transaction.CreateTransactionalRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.TransactionalResponse;
import com.mihenze.mscurse.paymentservice.rest.transaction.UpdateTransactionalRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionalMapper {
    TransactionalResponse mapToTransactionalResponse(Transaction transaction);

    Transaction mapToTransaction(CreateTransactionalRequest transactional);
    Transaction mapToTransaction(UpdateTransactionalRequest transactional);
}

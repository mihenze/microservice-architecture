package com.mihenze.mscurse.paymentservice.mapper;

import com.mihenze.mscurse.paymentservice.entity.Payment;
import com.mihenze.mscurse.paymentservice.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.payment.PaymentResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.UpdatePaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TransactionalMapper.class})
public interface PaymentMapper {
    PaymentResponse mapToPaymentResponse(Payment payment);

    Payment mapToPayment(CreatePaymentRequest payment);
    Payment mapToPayment(UpdatePaymentRequest payment);
}

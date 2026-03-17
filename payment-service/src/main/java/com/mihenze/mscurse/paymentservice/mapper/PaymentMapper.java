package com.mihenze.mscurse.paymentservice.mapper;

import com.mihenze.mscurse.paymentservice.dto.PaymentDto;
import com.mihenze.mscurse.paymentservice.entity.Payment;
import com.mihenze.mscurse.paymentservice.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.payment.PaymentResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.UpdatePaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface PaymentMapper {
    PaymentResponse mapToPaymentResponse(PaymentDto payment);

    Payment mapToPayment(PaymentDto payment);

    PaymentDto mapToPaymentDto(Payment payment);

    PaymentDto mapToPaymentDto(CreatePaymentRequest payment);

    PaymentDto mapToPaymentDto(UpdatePaymentRequest payment);
}

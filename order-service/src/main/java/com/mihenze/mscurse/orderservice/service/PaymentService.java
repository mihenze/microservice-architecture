package com.mihenze.mscurse.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.dtocommon.rest.enums.AsyncEventType;
import com.mihenze.mscurse.dtocommon.rest.enums.Currency;
import com.mihenze.mscurse.dtocommon.rest.enums.PaymentType;
import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.orderservice.config.BindingProperties;
import com.mihenze.mscurse.orderservice.entity.async.AsyncMessage;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageStatus;
import com.mihenze.mscurse.orderservice.enums.AsyncMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AsyncMessageService asyncMessageService;
    private final BindingProperties bindingProperties;
    private final ObjectMapper mapper;

    public void createAndSaveOrderPaymentMessage(Long orderId, BigDecimal amount) {

        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .type(PaymentType.CARD)
                .amount(amount)
                .currency(Currency.RUB)
                .orderId(orderId)
                .build();

        try {
            AsyncMessage asyncMessage = AsyncMessage.builder()
                    .bindingName(bindingProperties.getPaymentCreated())
                    .value(mapper.writeValueAsString(createPaymentRequest))
                    .payloadType(AsyncEventType.PAYMENT_CREATED)
                    .type(AsyncMessageType.OUTBOX)
                    .status(AsyncMessageStatus.CREATED)
                    .build();

            asyncMessageService.saveMessage(asyncMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

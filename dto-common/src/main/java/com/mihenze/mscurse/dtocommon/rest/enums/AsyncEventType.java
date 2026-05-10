package com.mihenze.mscurse.dtocommon.rest.enums;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AsyncEventType {
    PAYMENT_CREATED(CreatePaymentRequest.class),

    SHIPMENT_CREATED(CreateShipmentRequest.class);

    private final Class<?> payloadClass;
}

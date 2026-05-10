package com.mihenze.mscurse.dtocommon.rest.enums;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AsyncEventType {
    PAYMENT_CREATED(CreatePaymentRequest.class),
    PAYMENT_CONFIRM(PaymentResponse.class),
    SHIPMENT_CREATED(CreateShipmentRequest.class),
    SHIPMENT_CONFIRM(ShipmentResponse.class);

    private final Class<?> payloadClass;
}

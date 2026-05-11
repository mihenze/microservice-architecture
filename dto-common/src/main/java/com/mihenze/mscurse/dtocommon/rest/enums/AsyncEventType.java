package com.mihenze.mscurse.dtocommon.rest.enums;

import com.mihenze.mscurse.dtocommon.rest.payment.CreatePaymentRequest;
<<<<<<< task11-idempotent-event
import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.ShipmentResponse;
=======
import com.mihenze.mscurse.dtocommon.rest.shipment.CreateShipmentRequest;
>>>>>>> main
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AsyncEventType {
    PAYMENT_CREATED(CreatePaymentRequest.class),
<<<<<<< task11-idempotent-event
    PAYMENT_CONFIRM(PaymentResponse.class),
    SHIPMENT_CREATED(CreateShipmentRequest.class),
    SHIPMENT_CONFIRM(ShipmentResponse.class);
=======

    SHIPMENT_CREATED(CreateShipmentRequest.class);
>>>>>>> main

    private final Class<?> payloadClass;
}

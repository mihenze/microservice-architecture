package com.mihenze.mscurse.dtocommon.kafka;

import com.mihenze.mscurse.dtocommon.rest.enums.DeliveryMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationStatusMessage {
    Long orderId;
    OrderCreationStatus orderCreationStatus;
    Long paymentId;
    PaymentData paymentData;
    Long shipmentId;
    AddressData addressData;
    DeliveryMethod deliveryMethod;
}

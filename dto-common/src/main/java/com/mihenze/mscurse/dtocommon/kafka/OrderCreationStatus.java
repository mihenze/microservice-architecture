package com.mihenze.mscurse.dtocommon.kafka;

public enum OrderCreationStatus {
    DRAFT, // заказ создан, o-s -> p-s
    PAYMENT_WAITING, // ожидаем оплату, p-s -> o-s
    PAYMENT_CONFIRM, // оплата подтверждена, p-s -> o-s
    PAYMENT_ABORT, // оплата не подтверждена, p-s -> o-s
    PAYMENT_REFUNDED, // возврат оплаты, p-s -> o-s
    PROCESSED, // заказ оплачен o-s -> d-s
    DELIVERY_CREATED, // доставка создана, d-s -> o-s
    DELIVERY_COMPLETED, // доставка выполнена d-s -> o-s
    DELIVERY_CANCELED, // доставка отменена d-s -> o-s & p-s
}

package com.mihenze.mscurse.orderservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AsyncMessageType {
    INBOX("INBOX"), OUTBOX("OUTBOX");

    private final String code;
}

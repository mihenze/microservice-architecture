package com.mihenze.mscurse.orderservice.util;

import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

public class HeaderUtils {
    public static String getString(Message<?> message, String key) {

        Object value = message.getHeaders().get(key);

        if (value == null) return null;

        if (value instanceof byte[]) {
            return new String((byte[]) value, StandardCharsets.UTF_8);
        }

        return value.toString();
    }
}

package com.mihenze.mscurse.paymentservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimiterProperties {
    private Map<String, MethodRateLimiter> methods;

    @Data
    public static class MethodRateLimiter {
        private int limit;
        private Duration refreshPeriod;
        private Duration timeout;
    }
}

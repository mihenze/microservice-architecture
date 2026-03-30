package com.mihenze.mscurse.paymentservice.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mihenze.mscurse.paymentservice.config.RateLimiterProperties;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimiterService {
    private final RateLimiterProperties properties;

    private final Cache<String, RateLimiter> cache = Caffeine.newBuilder()
            .expireAfterAccess(Duration.ofMinutes(10))
            .maximumSize(1000)
            .build();

    public RateLimiter getLimiter(String methodName, String ip) {
        String key = methodName + "_" + ip;

        return cache.get(key, k -> {
            RateLimiterProperties.MethodRateLimiter methodConfig = properties.getMethods().get(methodName);

            if (methodConfig == null) {
                // дефолтные настройки
                methodConfig = new RateLimiterProperties.MethodRateLimiter();
                methodConfig.setLimit(20);
                methodConfig.setRefreshPeriod(Duration.ofMinutes(1));
                methodConfig.setTimeout(Duration.ZERO);
            }

            RateLimiterConfig config = RateLimiterConfig.custom()
                    .limitForPeriod(methodConfig.getLimit())
                    .limitRefreshPeriod(methodConfig.getRefreshPeriod())
                    .timeoutDuration(methodConfig.getTimeout())
                    .build();

            return RateLimiter.of(key, config);
        });
    }
}

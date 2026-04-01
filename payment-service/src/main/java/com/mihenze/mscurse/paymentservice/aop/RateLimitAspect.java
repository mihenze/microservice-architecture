package com.mihenze.mscurse.paymentservice.aop;

import com.mihenze.mscurse.paymentservice.exception.RateLimitExceededException;
import com.mihenze.mscurse.paymentservice.service.RateLimiterService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RateLimitAspect {
    private final RateLimiterService rateLimiterService;
    private final HttpServletRequest request;

    @Around("@annotation(rateLimitByIp)")
    public Object around(ProceedingJoinPoint pjp, RateLimitByIp rateLimitByIp) throws Throwable {
        // получаем имя из аннотации
        String nameFromAnnotation = rateLimitByIp.name();

        // если не указано, используем имя метода
        String methodName = nameFromAnnotation.isEmpty() ? pjp.getSignature().getName() : nameFromAnnotation;

        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        RateLimiter rateLimiter = rateLimiterService.getLimiter(methodName, ip);

        boolean permitted = rateLimiter.acquirePermission();
        if (!permitted) {
            throw new RateLimitExceededException("Too many requests. Try again later.");
        }

        return pjp.proceed();
    }
}

package com.mihenze.mscurse.paymentservice.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihenze.mscurse.paymentservice.entity.IdempotencyStatus;
import com.mihenze.mscurse.paymentservice.service.IdempotencyDbService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class IdempotencyAspect {
    private final IdempotencyDbService service;
    private final ObjectMapper objectMapper;

    @Around("@annotation(Idempotent)")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String key = request.getHeader("X-Idempotency-Key");
        if (key == null) {
            throw new IllegalArgumentException("X-Idempotency-Key required");
        }

        var existing = service.tryCreate(key);

        if (existing.isPresent()) {
            var entity = existing.get();
            if (entity.getIdempotencyStatus() == IdempotencyStatus.PENDING) {
                throw new RuntimeException("Request in progress");
            }

            return ResponseEntity.status(entity.getStatusCode()).body(entity.getResponse());
        }

        try {
            Object result = joinPoint.proceed();

            if (result instanceof ResponseEntity<?> response) {
                service.markCompleted(key, objectMapper.writeValueAsString(response.getBody()),
                        response.getStatusCode().value());
            }

            return result;

        } catch (Exception ex) {
            service.markCompleted(key, ex.getMessage(), 500);
            throw ex;
        }
    }
}

package com.mihenze.mscurse.orderservice.feign;

import com.mihenze.mscurse.orderservice.exception.ClientBadRequestException;
import com.mihenze.mscurse.orderservice.exception.ClientConflictException;
import com.mihenze.mscurse.orderservice.exception.InternalPaymentServiceClientException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FeignExceptionAspect {
    @Around("@annotation(com.mihenze.mscurse.orderservice.feign.FeignExceptionHandled)")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (FeignException exception) {
            String methodName = joinPoint.getSignature().getName();
            throw  handleFeignException(exception, methodName);
        }
    }

    private Exception handleFeignException(
            Throwable cause,
            String methodName) {
        FeignException feignException = (FeignException) cause;

        int status = feignException.status();
        String responseBody = feignException.contentUTF8();

        if (status == -1) {
            log.error("Service is unreachable");
        } else {
            log.error("Feign error");
        }

        log.error("Feign error (method={}, status={}, body={})",
                methodName, status, responseBody, cause);

        if (status == 400) {
            return new ClientBadRequestException(feignException.getMessage());
        }

        if (status == 409) {
            return new ClientConflictException(feignException.getMessage());
        }

        return new InternalPaymentServiceClientException("Fallback: payment service is temporarily unavailable");
    }
}

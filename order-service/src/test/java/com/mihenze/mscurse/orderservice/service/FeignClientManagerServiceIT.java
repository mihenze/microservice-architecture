package com.mihenze.mscurse.orderservice.service;

import com.mihenze.mscurse.dtocommon.rest.payment.PaymentResponse;
import com.mihenze.mscurse.orderservice.exception.ClientBadRequestException;
import com.mihenze.mscurse.orderservice.exception.ClientConflictException;
import com.mihenze.mscurse.orderservice.exception.InternalPaymentServiceClientException;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@EnableWireMock( // Включает и настраивает WireMock сервер для мокирования внешних HTTP-запросов
        @ConfigureWireMock(
                name = "payment-service", // Название мок-сервиса
                port = 9999, // Порт, на котором работает WireMock
                baseUrlProperties = "http://localhost", // Базовый URL для клиента
                filesUnderClasspath = "wiremock" // Папка с файлами сценариев мок-ответов
        )
)
public class FeignClientManagerServiceIT {
    @Autowired
    private FeignClientManagerService feignClientManagerService;

    @Test
    void testCreatePayment_Success() {
        ResponseEntity<PaymentResponse> response = feignClientManagerService
                .createPayment(1L, BigDecimal.valueOf(22.5));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(postRequestedFor(urlEqualTo("/api/v1/payments")));
    }

    @Test
    void testReserveTime_Conflict() {
        Assertions.assertThrows(ClientConflictException.class, () -> {
            feignClientManagerService
                    .createPayment(4L, BigDecimal.valueOf(22.5));
        });
    }

    @Test
    void testReserveTime_ServerError() {
        Assertions.assertThrows(InternalPaymentServiceClientException.class, () -> {
            feignClientManagerService
                    .createPayment(3L, BigDecimal.valueOf(22.5));
        });
    }

    @Test
    void testCreatePayment_BadRequest() {
        Assertions.assertThrows(ClientBadRequestException.class, () -> {
            feignClientManagerService
                    .createPayment(2L, BigDecimal.valueOf(22.5));
        });
    }
}

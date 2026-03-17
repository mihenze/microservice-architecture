package com.mihenze.mscurse.paymentservice.controller.doc;

import com.mihenze.mscurse.paymentservice.rest.PaymentErrorResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.CreatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.payment.PaymentResponse;
import com.mihenze.mscurse.paymentservice.rest.payment.UpdatePaymentRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.CreateTransactionRequest;
import com.mihenze.mscurse.paymentservice.rest.transaction.TransactionResponse;
import com.mihenze.mscurse.paymentservice.rest.transaction.UpdateTransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Payment Service API", description = "Сервис Оплаты")
public interface PaymentControllerDoc {
    @Operation(summary = "Получить оплату по id с транзакциями")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация об оплате получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Оплата не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<PaymentResponse> getPayment(@Parameter(description = "Идентификатор оплаты") Long id);

    @Operation(summary = "Создать оплату")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новая оплата успешно создана",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request);

    @Operation(summary = "Обновить оплату")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Оплата успешно обновлена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<PaymentResponse> updatePayment(@RequestBody UpdatePaymentRequest request);
    @Operation(summary = "Удалить оплату")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Оплата успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Оплата не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<Void> deletePayment(@Parameter(description = "Идентификатор оплаты") Long id);

    @Operation(summary = "Создать транзакцию")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новая транзакция успешно создана",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<TransactionResponse> createTransactional(@Parameter(description = "Идентификатор оплаты") Long id,
                                                            @RequestBody CreateTransactionRequest request);

    @Operation(summary = "Обновить статус транзакции")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Транзакция успешно обновлена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<TransactionResponse> updateTransactional(@Parameter(description = "Идентификатор оплаты") Long id,
                                                            @RequestBody UpdateTransactionRequest request);

    @Operation(summary = "Удалить транзакцию")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Транзакция успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Транзакция не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentErrorResponse.class)))
    })
    ResponseEntity<Void> deleteTransactional(@Parameter(description = "Идентификатор транзакции") Long id);
}

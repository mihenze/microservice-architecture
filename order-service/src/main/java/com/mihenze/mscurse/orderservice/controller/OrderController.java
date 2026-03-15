package com.mihenze.mscurse.orderservice.controller;

import com.mihenze.mscurse.orderservice.rest.OrderErrorResponse;
import com.mihenze.mscurse.orderservice.rest.order.CreateOrderRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderResponse;
import com.mihenze.mscurse.orderservice.rest.order.UpdateOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Order Service API", description = "Сервис Заказов")
public interface OrderController {
    @Operation(summary = "Создать заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новый заказ успешно создан",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class)))
    })
    ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request);

    @Operation(summary = "Обновить заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ успешно обновлен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class)))
    })
    ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest request);

    @Operation(summary = "Получить заказ по идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация о заказе получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class)))
    })
    ResponseEntity<OrderResponse> getOrder(@Parameter(description = "Идентификатор заказа") Long orderId);

    @Operation(summary = "Получить список всех заказов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список заказов получен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class)))
    })
    ResponseEntity<List<OrderResponse>> getOrders();

    @Operation(summary = "Удалить заказ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ успешно удален"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderErrorResponse.class)))
    })
    ResponseEntity<Void> deleteOrder(@Parameter(description = "Идентификатор заказа") Long orderId);

}

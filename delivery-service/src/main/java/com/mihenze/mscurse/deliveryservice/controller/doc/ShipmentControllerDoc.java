package com.mihenze.mscurse.deliveryservice.controller.doc;

import com.mihenze.mscurse.deliveryservice.rest.DeliveryErrorResponse;
import com.mihenze.mscurse.deliveryservice.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.deliveryservice.rest.shipment.ShipmentResponse;
import com.mihenze.mscurse.deliveryservice.rest.shipment.UpdateShipmentRequest;
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

@Tag(name = "Delivery Service API - shipment", description = "Сервис Доставки - отправление")
public interface ShipmentControllerDoc {
    @Operation(summary = "Получить полную информацию об отправлении по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация об отправлении получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShipmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Оплата не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<ShipmentResponse> getShipment(@Parameter(description = "Идентификатор отправления") Long id);

    @Operation(summary = "Создать отправление")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новое отправление успешно создано",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShipmentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<ShipmentResponse> createShipment(@RequestBody CreateShipmentRequest request);

    @Operation(summary = "Обновить отправление")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Отправление успешно обновлено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShipmentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<ShipmentResponse> updateShipment(@RequestBody UpdateShipmentRequest request);
    @Operation(summary = "Удалить отправление")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Отправление успешно удалено"),
            @ApiResponse(responseCode = "404", description = "Отправление не найдено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<Void> deleteShipment(@Parameter(description = "Идентификатор отправления") Long id);
}

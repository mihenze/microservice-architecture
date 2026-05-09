package com.mihenze.mscurse.deliveryservice.controller.doc;

import com.mihenze.mscurse.deliveryservice.rest.DeliveryErrorResponse;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.CreateTrackingEventRequest;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.UpdateTrackingEventRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.trackingevent.TrackingEventResponse;
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

@Tag(name = "Delivery Service API - tracking", description = "Сервис Доставки - отслеживание")
public interface TrackingEventControllerDoc {
    @Operation(summary = "Получить отслеживание по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация об отслеживании получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrackingEventResponse.class))),
            @ApiResponse(responseCode = "404", description = "Оплата не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<TrackingEventResponse> getTrackingEvent(@Parameter(description = "Идентификатор отслеживания") Long id);

    @Operation(summary = "Создать отслеживание")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новое отслеживание успешно создано",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrackingEventResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<TrackingEventResponse> createTrackingEvent(
            @Parameter(description = "Идентификатор отправления") Long shipmentId,
            @RequestBody CreateTrackingEventRequest request);

    @Operation(summary = "Обновить отслеживание")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Отслеживание успешно обновлено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrackingEventResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<TrackingEventResponse> updateTrackingEvent(@RequestBody UpdateTrackingEventRequest request);
    @Operation(summary = "Удалить отслеживание")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Отслеживание успешно удалено"),
            @ApiResponse(responseCode = "404", description = "Отслеживание не найдено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<Void> deleteTrackingEvent(@Parameter(description = "Идентификатор отслеживания") Long id);
}

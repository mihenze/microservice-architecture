package com.mihenze.mscurse.deliveryservice.controller.doc;

import com.mihenze.mscurse.deliveryservice.rest.DeliveryErrorResponse;
import com.mihenze.mscurse.deliveryservice.rest.packages.CreatePackageRequest;
import com.mihenze.mscurse.deliveryservice.rest.packages.UpdatePackageRequest;
import com.mihenze.mscurse.dtocommon.rest.shipment.packages.PackageResponse;
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

@Tag(name = "Delivery Service API - package", description = "Сервис Доставки - посылка")
public interface PackageControllerDoc {
    @Operation(summary = "Получить посылку по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Оплата не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<PackageResponse> getPackage(@Parameter(description = "Идентификатор посылки") Long id);

    @Operation(summary = "Создать посылку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Новая посылка успешно создана",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<PackageResponse> createPackage(@Parameter(description = "Идентификатор отправления") Long shipmentId,
                                                  @RequestBody CreatePackageRequest request);

    @Operation(summary = "Обновить посылку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка успешно обновлена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Переданы некорректные данные",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<PackageResponse> updatePackage(@RequestBody UpdatePackageRequest request);
    @Operation(summary = "Удалить посылку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Посылка успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Посылка не найдена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeliveryErrorResponse.class)))
    })
    ResponseEntity<Void> deletePackage(@Parameter(description = "Идентификатор посылки") Long id);
}

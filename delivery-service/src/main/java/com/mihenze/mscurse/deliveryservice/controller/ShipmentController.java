package com.mihenze.mscurse.deliveryservice.controller;

import com.mihenze.mscurse.deliveryservice.controller.doc.ShipmentControllerDoc;
import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.deliveryservice.rest.shipment.CreateShipmentRequest;
import com.mihenze.mscurse.deliveryservice.rest.shipment.ShipmentResponse;
import com.mihenze.mscurse.deliveryservice.rest.shipment.UpdateShipmentRequest;
import com.mihenze.mscurse.deliveryservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController implements ShipmentControllerDoc {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;

    @GetMapping("/{shipment_id}")
    @Override
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable("shipment_id") Long id) {
        return ResponseEntity.ok(shipmentMapper.mapToShipmentResponse(shipmentService.getShipmentById(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody CreateShipmentRequest request) {
        return ResponseEntity.ok(
                shipmentMapper.mapToShipmentResponse(shipmentService.createShipment(shipmentMapper.mapToShipmentDto(request))));
    }

    @PutMapping
    @Override
    public ResponseEntity<ShipmentResponse> updateShipment(@RequestBody UpdateShipmentRequest request) {
        return ResponseEntity.ok(
                shipmentMapper.mapToShipmentResponse(shipmentService.updateShipment(shipmentMapper.mapToShipmentDto(request))));
    }

    @DeleteMapping("/{shipment_id}")
    @Override
    public ResponseEntity<Void> deleteShipment(@PathVariable("shipment_id") Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.ok().build();
    }
}

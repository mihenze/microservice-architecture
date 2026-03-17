package com.mihenze.mscurse.deliveryservice.controller;

import com.mihenze.mscurse.deliveryservice.controller.doc.TrackingEventControllerDoc;
import com.mihenze.mscurse.deliveryservice.mapper.TrackingEventMapper;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.CreateTrackingEventRequest;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.TrackingEventResponse;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.UpdateTrackingEventRequest;
import com.mihenze.mscurse.deliveryservice.service.TrackingEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class TrackingEventController implements TrackingEventControllerDoc {
    private final TrackingEventService trackingEventService;
    private final TrackingEventMapper trackingEventMapper;

    @GetMapping("/tracking/{tracking_id}")
    @Override
    public ResponseEntity<TrackingEventResponse> getTrackingEvent(@PathVariable("tracking_id") Long id) {
        return ResponseEntity.ok(trackingEventMapper.mapToTrackingEventResponse(
                trackingEventService.getTrackingEventById(id)));
    }

    @PostMapping("/{shipment_id}/tracking")
    @Override
    public ResponseEntity<TrackingEventResponse> createTrackingEvent(@PathVariable("shipment_id") Long shipmentId,
                                                                     @RequestBody CreateTrackingEventRequest request) {
        return ResponseEntity.ok(trackingEventMapper.mapToTrackingEventResponse(trackingEventService
                .createTrackingEvent(shipmentId, trackingEventMapper.mapToTrackingEventDto(request))));
    }

    @PatchMapping("/tracking")
    @Override
    public ResponseEntity<TrackingEventResponse> updateTrackingEvent(@RequestBody UpdateTrackingEventRequest request) {
        return ResponseEntity.ok(trackingEventMapper.mapToTrackingEventResponse(trackingEventService
                .updateTrackingEvent(trackingEventMapper.mapToTrackingEventDto(request))));
    }

    @DeleteMapping("/{tracking_id}")
    @Override
    public ResponseEntity<Void> deleteTrackingEvent(@PathVariable("tracking_id") Long id) {
        trackingEventService.deleteTrackingEvent(id);
        return ResponseEntity.ok().build();
    }
}

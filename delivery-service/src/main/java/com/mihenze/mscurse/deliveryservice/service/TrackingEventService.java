package com.mihenze.mscurse.deliveryservice.service;

import com.mihenze.mscurse.deliveryservice.dto.PackageDto;
import com.mihenze.mscurse.deliveryservice.dto.ShipmentDto;
import com.mihenze.mscurse.deliveryservice.dto.TrackingEventDto;
import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import com.mihenze.mscurse.deliveryservice.entity.TrackingEvent;
import com.mihenze.mscurse.deliveryservice.entity.TrackingStatus;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundShipmentException;
import com.mihenze.mscurse.deliveryservice.exception.NotFoundTrackingEventException;
import com.mihenze.mscurse.deliveryservice.mapper.ShipmentMapper;
import com.mihenze.mscurse.deliveryservice.mapper.TrackingEventMapper;
import com.mihenze.mscurse.deliveryservice.repository.ShipmentRepository;
import com.mihenze.mscurse.deliveryservice.repository.TrackingEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackingEventService {
    private final TrackingEventRepository trackingEventRepository;
    private final TrackingEventMapper trackingEventMapper;
    private final ShipmentRepository shipmentRepository;

    public TrackingEventDto getTrackingEventById(Long id) {
        TrackingEvent trackingEvent = trackingEventRepository.findById(id)
                .orElseThrow(() -> new NotFoundTrackingEventException(id));

        return trackingEventMapper.mapToTrackingEventDto(trackingEvent);
    }

    @Transactional
    public TrackingEventDto createTrackingEvent(Long id, TrackingEventDto trackingEventDto) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundShipmentException(id));

        TrackingEvent newTrackingEvent = trackingEventMapper.mapToTrackingEvent(trackingEventDto);
        newTrackingEvent.setShipment(shipment);
        newTrackingEvent.setStatus(TrackingStatus.PACKAGE_PREPARED);

        TrackingEvent saved = trackingEventRepository.save(newTrackingEvent);
        return trackingEventMapper.mapToTrackingEventDto(saved);
    }

    @Transactional
    public TrackingEventDto updateTrackingEvent(TrackingEventDto trackingEventDto) {
        TrackingEvent newTrackingEvent = trackingEventMapper.mapToTrackingEvent(trackingEventDto);

        TrackingEvent trackingEvent = trackingEventRepository.findById(newTrackingEvent.getId())
                .orElseThrow(() -> new NotFoundTrackingEventException(newTrackingEvent.getId()));

        trackingEvent.setStatus(newTrackingEvent.getStatus());
        trackingEvent.setCity(newTrackingEvent.getCity());

        TrackingEvent saved = trackingEventRepository.save(trackingEvent);
        return trackingEventMapper.mapToTrackingEventDto(saved);
    }

    @Transactional
    public void deleteTrackingEvent(Long id) {
        if (trackingEventRepository.existsById(id)) {
            throw new NotFoundTrackingEventException(id);
        }
    }
}

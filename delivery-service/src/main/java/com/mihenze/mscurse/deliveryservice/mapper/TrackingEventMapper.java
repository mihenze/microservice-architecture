package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.TrackingEventDto;
import com.mihenze.mscurse.deliveryservice.entity.TrackingEvent;
import com.mihenze.mscurse.deliveryservice.rest.shipment.TrackingEventResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackingEventMapper {
    TrackingEvent mapToTrackingEvent(TrackingEventDto trackingEvent);

    TrackingEventDto mapToTrackingEventDto(TrackingEvent trackingEvent);

    TrackingEventResponse mapToTrackingEventResponse(TrackingEventDto trackingEvent);
}

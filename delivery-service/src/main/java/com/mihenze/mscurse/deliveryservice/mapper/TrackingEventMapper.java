package com.mihenze.mscurse.deliveryservice.mapper;

import com.mihenze.mscurse.deliveryservice.dto.TrackingEventDto;
import com.mihenze.mscurse.deliveryservice.entity.TrackingEvent;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.CreateTrackingEventRequest;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.TrackingEventResponse;
import com.mihenze.mscurse.deliveryservice.rest.trackingevent.UpdateTrackingEventRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackingEventMapper {
    TrackingEvent mapToTrackingEvent(TrackingEventDto trackingEvent);

    TrackingEventDto mapToTrackingEventDto(TrackingEvent trackingEvent);

    TrackingEventDto mapToTrackingEventDto(CreateTrackingEventRequest trackingEvent);

    TrackingEventDto mapToTrackingEventDto(UpdateTrackingEventRequest trackingEvent);

    TrackingEventResponse mapToTrackingEventResponse(TrackingEventDto trackingEvent);
}

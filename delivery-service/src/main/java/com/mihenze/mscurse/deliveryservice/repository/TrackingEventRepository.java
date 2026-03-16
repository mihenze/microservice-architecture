package com.mihenze.mscurse.deliveryservice.repository;

import com.mihenze.mscurse.deliveryservice.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
}

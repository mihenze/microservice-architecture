package com.mihenze.mscurse.deliveryservice.repository;

import com.mihenze.mscurse.deliveryservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    boolean existsByTrackingNumber(String trackingNumber);
}

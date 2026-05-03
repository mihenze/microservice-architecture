package com.mihenze.mscurse.deliveryservice.entity;

import com.mihenze.mscurse.dtocommon.rest.enums.TrackingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tracking_event")
public class TrackingEvent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "TrackingEvent's description must not be null")
    private String description;

    @Column(name = "status", nullable = false)
    @NotNull(message = "TrackingEvent's status must not be null")
    @Enumerated(EnumType.STRING)
    private TrackingStatus status;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "TrackingEvent's city must not be null")
    private String city;

    @UpdateTimestamp
    @Column(name = "event_time", nullable = false)
    private Instant eventTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    Shipment shipment;
}

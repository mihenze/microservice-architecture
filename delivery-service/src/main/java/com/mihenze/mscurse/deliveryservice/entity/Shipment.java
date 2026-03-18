package com.mihenze.mscurse.deliveryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment")
public class Shipment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    @NotNull(message = "Shipment's orderId must not be null")
    private Long orderId;

    @Column(name = "address_region", nullable = false)
    @NotBlank(message = "Shipment's addressRegion must not be null")
    private String addressRegion;

    @Column(name = "address_city", nullable = false)
    @NotBlank(message = "Shipment's addressRegion must not be null")
    private String addressCity;

    @Column(name = "address_street", nullable = false)
    @NotBlank(message = "Shipment's addressRegion must not be null")
    private String addressStreet;

    @Column(name = "address_postcode", nullable = false)
    @NotBlank(message = "Shipment's addressRegion must not be null")
    private String addressPostcode;

    @Column(name = "address_building", nullable = false)
    @NotNull(message = "Shipment's addressBuilding must not be null")
    private Integer addressBuilding;

    @Column(name = "address_apartment")
    private Integer addressApartment;

    @Column(name = "delivery_method", nullable = false)
    @NotNull(message = "Shipment's deliveryMethod must not be null")
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Shipment's status must not be null")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Column(name = "tracking_number", nullable = false)
    @NotBlank(message = "Shipment's trackingNumber must not be null")
    private String trackingNumber;

    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Package> packages;

    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackingEvent> events;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}

package com.mihenze.mscurse.deliveryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "package")
public class Package {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "length", nullable = false)
    @NotNull(message = "Package's length must not be null")
    private Double length;

    @Column(name = "width", nullable = false)
    @NotNull(message = "Package's width must not be null")
    private Double width;

    @Column(name = "height", nullable = false)
    @NotNull(message = "Package's height must not be null")
    private Double height;

    @Column(name = "dimension_unit", nullable = false)
    @NotBlank(message = "Package's dimensionUnit must not be null")
    private String dimensionUnit;

    @Column(name = "weight_value", nullable = false)
    @NotNull(message = "Package's weightValue must not be null")
    private Double weightValue;

    @Column(name = "weight_unit", nullable = false)
    @NotBlank(message = "Package's weightUnit must not be null")
    private String weightUnit;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Package's status must not be null")
    @Enumerated(EnumType.STRING)
    private PackageStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    Shipment shipment;
}

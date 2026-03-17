package com.mihenze.mscurse.orderservice.entity;

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
@Table(name = "delivery_address")
public class DeliveryAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region", nullable = false)
    @NotBlank(message = "DeliveryAddress's region must not be null")
    private String region;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "DeliveryAddress's city must not be null")
    private String city;

    @Column(name = "street", nullable = false)
    @NotBlank(message = "DeliveryAddress's street must not be null")
    private String street;

    @Column(name = "building", nullable = false)
    @NotNull(message = "DeliveryAddress's building must not be null")
    private Integer building;

    @Column(name = "apartment")
    @NotNull(message = "DeliveryAddress's apartment must not be null")
    private Integer apartment;

    @Column(name = "postcode", nullable = false)
    @NotNull(message = "DeliveryAddress's postcode must not be null")
    private Integer postcode;
}

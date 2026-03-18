package com.mihenze.mscurse.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", nullable = false)
    @NotBlank(message = "Order's uid must not be null")
    private String uid;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Order's status must not be null")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "cost", precision = 19, scale = 2, nullable = false)
    @NotNull(message = "Order's cost must not be null")
    private BigDecimal cost;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id")
    private DeliveryAddress address;
}

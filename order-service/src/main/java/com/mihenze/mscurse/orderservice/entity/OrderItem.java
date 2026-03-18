package com.mihenze.mscurse.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "OrderItem's name must not be null")
    private String name;

    @Column(name = "count")
    @NotNull(message = "OrderItem's count must not be null")
    @Min(value = 1, message = "OrderItem's count must be greater than zero")
    private Integer count;

    @Column(name = "cost", precision = 19, scale = 2, nullable = false)
    @NotNull(message = "OrderItem's cost must not be null")
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}

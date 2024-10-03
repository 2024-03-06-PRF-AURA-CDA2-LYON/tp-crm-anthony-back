package com.banana.mycrm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {
    public enum OrderState {
        CANCELED,
        OPTION,
        CONFIRMED,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 100, name="service_type", nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private Integer tva;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Customer client;

    @Column(name="nb_days", nullable = false)
    private Integer nbDays;

    @Column(name="total_exclude_tax", nullable = false)
    private Integer totalExcludeTax;

    @Enumerated(EnumType.ORDINAL)
    @Column()
    private OrderState state;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comment;
}

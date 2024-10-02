package com.banana.mycrm.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(length = 100, nullable = false)
    private String service_type;

    @Column(nullable = false)
    private int TVA;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Customer client;

    @Column(nullable = false)
    private int nb_days;

    @Column(nullable = false)
    private int total_exclude_tax;

    @Column()
    private int state;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comment;
}

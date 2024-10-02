package com.banana.mycrm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(length = 100, nullable = false)
    private String company_name;

    @Column(length = 50, nullable = false)
    private String first_name;

    @Column(length = 50, nullable = false)
    private String last_name;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 15, unique = true)
    private String phone_number;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(length = 30, nullable = false)
    private String zip_code;

    @Column(length = 100, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String city;

    @Column()
    private int state;
}

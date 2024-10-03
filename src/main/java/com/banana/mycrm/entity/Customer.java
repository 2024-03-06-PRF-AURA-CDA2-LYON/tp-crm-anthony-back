package com.banana.mycrm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Customer {
    public enum CustomerState {
        ACTIVE,
        INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 100, nullable = false, name = "company_name")
    private String companyName;

    @Column(length = 50, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 50, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 15, unique = true, name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(length = 30, nullable = false, name = "zip_code")
    private String zipCode;

    @Column(length = 100, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String city;

    @Enumerated(EnumType.ORDINAL)
    @Column()
    private CustomerState state;
}

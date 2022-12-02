package com.cdsoft.dialogflowserver.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Customer customer;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "house_number")
    private int houseNumber;
}


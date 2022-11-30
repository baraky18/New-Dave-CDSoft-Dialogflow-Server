package com.cdsoft.dialogflowserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
//@Data
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
    private Customer customer;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "house_number")
    private int houseNumber;

    public Country getCountry() {
        return this.country;
    }

    public City getCity() {
        return this.city;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }
}


package com.harshal.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    private String mobile;

    public Address(){

    }

    public Address(long id, String firstName, String lastName, String streetAddress, String city, String state,
            String zipCode, User user, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
        this.mobile = mobile;
    }

}

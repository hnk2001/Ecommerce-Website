package com.harshal.ecommerce.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    private double rating;

    private LocalDateTime createdAt;

    public Rating() {
    }

    public Rating(long id, User user, Product product, double rating, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    
    
}

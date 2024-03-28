package com.harshal.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String color;

    @Embedded
    @ElementCollection
    private Set<Size> sizes = new HashSet<>();

    private String imageUrl;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private int numRatings;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private LocalDateTime createdAt;

    public Product(){

    }

    public Product(long id, String title, String description, int price, int discountPercent, int quantity,
            String brand, String color, Set<Size> sizes, String imageUrl, List<Rating> ratings, List<Review> reviews,
            int numRatings, Category category, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.brand = brand;
        this.color = color;
        this.sizes = sizes;
        this.imageUrl = imageUrl;
        this.ratings = ratings;
        this.reviews = reviews;
        this.numRatings = numRatings;
        this.category = category;
        this.createdAt = createdAt;
    }
    
}

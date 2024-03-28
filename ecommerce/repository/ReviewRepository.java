package com.harshal.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harshal.ecommerce.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> getAllProductsReview(@Param("productId") Long productId);
    
}

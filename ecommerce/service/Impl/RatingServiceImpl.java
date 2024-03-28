package com.harshal.ecommerce.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Product;
import com.harshal.ecommerce.model.Rating;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.RatingRepository;
import com.harshal.ecommerce.request.RatingRequest;
import com.harshal.ecommerce.service.ProductService;
import com.harshal.ecommerce.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProducttId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
    
}

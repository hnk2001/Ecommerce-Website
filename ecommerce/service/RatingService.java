package com.harshal.ecommerce.service;

import java.util.List;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Rating;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.request.RatingRequest;

public interface RatingService {
    
    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}

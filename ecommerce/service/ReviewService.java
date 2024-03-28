package com.harshal.ecommerce.service;

import java.util.List;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Review;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.request.ReviewRequest;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}

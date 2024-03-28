package com.harshal.ecommerce.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Product;
import com.harshal.ecommerce.model.Review;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.ProductRepository;
import com.harshal.ecommerce.repository.ReviewRepository;
import com.harshal.ecommerce.request.ReviewRequest;
import com.harshal.ecommerce.service.ProductService;
import com.harshal.ecommerce.service.ReviewService;

@Service
public class ReviewServiceImpl implements  ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProducttId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        
        return reviewRepository.getAllProductsReview(productId);
    }
    
}

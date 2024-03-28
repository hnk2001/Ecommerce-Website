package com.harshal.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.Rating;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.request.RatingRequest;
import com.harshal.ecommerce.service.RatingService;
import com.harshal.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt) throws UserException, ProductException{

        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.createRating(req, user);

        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId,
        @RequestHeader("Authorization")String jwt) throws UserException, ProductException{

            User user = userService.findUserProfileByJwt(jwt);

            List<Rating> ratings = ratingService.getProductsRating(productId);

            return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }

    
}

package com.harshal.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.ecommerce.exception.CartItemException;
import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.CartItem;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.response.ApiResponse;
import com.harshal.ecommerce.service.CartItemService;
import com.harshal.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;
    
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException{

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("cart item deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException{

        User user = userService.findUserProfileByJwt(jwt);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }
    
}

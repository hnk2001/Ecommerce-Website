package com.harshal.ecommerce.service;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Cart;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}

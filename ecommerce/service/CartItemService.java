package com.harshal.ecommerce.service;

import com.harshal.ecommerce.exception.CartItemException;
import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.Cart;
import com.harshal.ecommerce.model.CartItem;
import com.harshal.ecommerce.model.Product;

public interface CartItemService {
    
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}

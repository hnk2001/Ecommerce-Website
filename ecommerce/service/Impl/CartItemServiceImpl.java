package com.harshal.ecommerce.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.exception.CartItemException;
import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.Cart;
import com.harshal.ecommerce.model.CartItem;
import com.harshal.ecommerce.model.Product;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.CartItemRepository;
import com.harshal.ecommerce.repository.CartRepository;
import com.harshal.ecommerce.service.CartItemService;
import com.harshal.ecommerce.service.UserService;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id: "+cartItemId);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if(user.getId() == reqUser.getId()){
            cartItemRepository.deleteById(cartItemId);
        }else{
            throw new UserException("You can't remove another users item");
        }
        
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(cartItemId);
        User user= userService.findUserById(item.getUserId());

        if(user.getId() == (userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }
        return cartItemRepository.save(item);
    }
    
}

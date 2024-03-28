package com.harshal.ecommerce.service.Impl;

import java.util.Set;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.exception.ProductException;
import com.harshal.ecommerce.model.Cart;
import com.harshal.ecommerce.model.CartItem;
import com.harshal.ecommerce.model.Product;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.CartRepository;
import com.harshal.ecommerce.request.AddItemRequest;
import com.harshal.ecommerce.service.CartItemService;
import com.harshal.ecommerce.service.CartService;
import com.harshal.ecommerce.service.ProductService;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
        
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);

            int price = req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "Item Add To Cart";
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    // @Override
    // public Cart findUserCart(Long userId) {
    //     Cart cart = cartRepository.findByUserId(userId);
        
    //     int totalPrice = 0;
    //     int totalDiscountedPrice = 0;
    //     int totalItem=0;

    //     for(CartItem cartItem : cart.getCartItems()){
    //         System.out.println(cartItem);
    //         totalPrice = totalPrice + cartItem.getPrice();
    //         totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
    //         totalItem = totalItem + cartItem.getQuantity();
    //     }

    //     // int totalPrice = cart.getCartItems().stream().mapToInt(CartItem::getPrice).sum();
    //     // int totalDiscountedPrice = cart.getCartItems().stream().mapToInt(CartItem::getDiscountedPrice).sum();
    //     // int totalItem = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
        
    //     cart.setTotalDiscountedPrice(totalDiscountedPrice);
    //     cart.setTotalItem(totalItem);
    //     cart.setTotalPrice(totalPrice);
    //     cart.setDiscount(totalPrice-totalDiscountedPrice);
    //     return cartRepository.save(cart);
    // }

    // @Override
    // public Cart findUserCart(Long userId) {
    //     Optional<Cart> cartOptional = cartRepository.findById(userId);
    //     if (cartOptional.isPresent()) {
    //         Cart cart = cartOptional.get();
    //         int totalDiscountedPrice = 0;
    //         int totalItem = 0;

    //         for (CartItem cartItem : cart.getCartItems()) {
    //             totalDiscountedPrice += cartItem.getDiscountedPrice() * cartItem.getQuantity();
    //             totalItem += cartItem.getQuantity();
    //         }

    //         cart.setTotalDiscountedPrice(totalDiscountedPrice);
    //         cart.setTotalItem(totalItem);
    //         cart.setTotalPrice(cart.getTotalPrice()); // You might want to retrieve total price from database instead of calculating it again
    //         cart.setDiscount((int)cart.getTotalPrice() - totalDiscountedPrice);
    //         return cartRepository.save(cart);
    //     } else {
    //         return null; // Or throw appropriate exception if needed
    //     }
    // }
    // ... other service methods

    @Override
    public Cart findUserCart(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findById(userId);
        return cartOptional.map(cart -> {
            calculateCartTotals(cart);
            return cartRepository.save(cart);
        }).orElse(null); // Or create a new cart if needed
    }

    private void calculateCartTotals(Cart cart) {
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice + cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount((int)cart.getTotalPrice() - totalDiscountedPrice);
    }
}

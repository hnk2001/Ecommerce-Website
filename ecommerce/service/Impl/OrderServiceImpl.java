package com.harshal.ecommerce.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.exception.OrderException;
import com.harshal.ecommerce.model.Address;
import com.harshal.ecommerce.model.Cart;
import com.harshal.ecommerce.model.CartItem;
import com.harshal.ecommerce.model.OrderItem;
import com.harshal.ecommerce.model.Orders;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.AddressRepository;
import com.harshal.ecommerce.repository.CartRepository;
import com.harshal.ecommerce.repository.OrderItemRepository;
import com.harshal.ecommerce.repository.OrderRepository;
import com.harshal.ecommerce.repository.UserRepository;
import com.harshal.ecommerce.service.CartService;
import com.harshal.ecommerce.service.OrderItemService;
import com.harshal.ecommerce.service.OrderService;
import com.harshal.ecommerce.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService{

  @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Orders findOrderById(Long orderId) throws OrderException {
        Optional<Orders> opt = orderRepository.findById(orderId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new OrderException("order not exist with id" + orderId);
    }

    @Override
    public Orders cancelledOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public Orders confirmedOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    @Override
    public Orders createOrder(User user, Address shippingAddress) {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item : cart.getCartItems()){
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }

        Orders createdOrder = new Orders();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItem(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        Orders savedOrder = orderRepository.save(createdOrder);

        for(OrderItem item : orderItems){
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);

        orderRepository.deleteById(orderId);
        
    }

    @Override
    public Orders deliveredOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders placedOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);
        order.setOrderStatus("Placed");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Orders shippedOrder(Long orderId) throws OrderException {
        Orders order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> usersOrderHistory(Long userId) {
        List<Orders> orders = orderRepository.getUsersOrders(userId);
        return orders;
    }
    
}

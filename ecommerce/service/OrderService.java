package com.harshal.ecommerce.service;

import java.util.List;

import com.harshal.ecommerce.exception.OrderException;
import com.harshal.ecommerce.model.Address;
import com.harshal.ecommerce.model.Orders;
import com.harshal.ecommerce.model.User;

public interface OrderService {
    public Orders createOrder(User user, Address shippingAddress);

    public List<Orders> usersOrderHistory(Long userId);

    public Orders findOrderById(Long orderId) throws OrderException;

    public Orders placedOrder(Long orderId) throws OrderException;

    public Orders confirmedOrder(Long orderId) throws OrderException;

    public Orders shippedOrder(Long orderId) throws OrderException;

    public Orders deliveredOrder(Long orderId) throws OrderException;

    public Orders cancelledOrder(Long orderId) throws OrderException;

    public List<Orders> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}

package com.harshal.ecommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.model.OrderItem;
import com.harshal.ecommerce.repository.OrderItemRepository;
import com.harshal.ecommerce.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    private OrderItemRepository orderItemRepository;
    


    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    
}

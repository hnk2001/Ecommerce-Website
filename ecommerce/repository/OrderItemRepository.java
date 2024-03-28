package com.harshal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshal.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}

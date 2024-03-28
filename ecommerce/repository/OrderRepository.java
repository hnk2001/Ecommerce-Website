package com.harshal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.harshal.ecommerce.model.Orders;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long>{
    
    @Query("SELECT o FROM Orders o WHERE o.user.id = :userId AND (o.orderStatus = 'PLACED' OR o.orderStatus = 'CONFIRMED' OR o.orderStatus = 'SHIPPED' OR o.orderStatus = 'DELIVERED')")
    List<Orders> getUsersOrders(@Param("userId") Long userId);
}

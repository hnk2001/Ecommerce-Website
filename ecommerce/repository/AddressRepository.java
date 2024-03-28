package com.harshal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshal.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}

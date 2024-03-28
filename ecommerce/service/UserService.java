package com.harshal.ecommerce.service;

import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.User;

public interface UserService {
    
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}

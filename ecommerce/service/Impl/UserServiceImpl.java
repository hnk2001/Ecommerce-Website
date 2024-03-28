package com.harshal.ecommerce.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshal.ecommerce.config.JwtProvider;
import com.harshal.ecommerce.exception.UserException;
import com.harshal.ecommerce.model.User;
import com.harshal.ecommerce.repository.UserRepository;
import com.harshal.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id: "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("user not found with email");
        }
        return user;
    }
    
}

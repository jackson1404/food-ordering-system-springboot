package com.jackson.food_ordering_system.user.service;

import com.jackson.food_ordering_system.user.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity findByUserName(String userName);

}

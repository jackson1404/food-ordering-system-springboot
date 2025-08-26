package com.jackson.food_ordering_system.user.service.serviceImpl;

import com.jackson.food_ordering_system.user.model.UserEntity;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import com.jackson.food_ordering_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}

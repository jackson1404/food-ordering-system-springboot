package com.jackson.food_ordering_system.auth.service;

import com.jackson.food_ordering_system.auth.constants.UserType;
import com.jackson.food_ordering_system.common.constant.Constants;
import com.jackson.food_ordering_system.resturant.constants.RestaurantStatus;
import com.jackson.food_ordering_system.resturant.controller.RestaurantStaffController;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import com.jackson.food_ordering_system.user.model.UserEntity;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (UserType.RESTAURANT_OWNER.equals(user.getRole())){
            RestaurantEntity restaurant = restaurantRepository.findByOwner(user)
                    .orElseThrow(() -> new UsernameNotFoundException("Restaurant not found"));
            if (!restaurant.getRestaurantStatus().equals(RestaurantStatus.APPROVED)){
                throw new DisabledException("Restaurant is not approved yet");
            }
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

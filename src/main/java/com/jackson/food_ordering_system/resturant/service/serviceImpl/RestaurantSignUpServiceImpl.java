/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.service.serviceImpl;

import com.jackson.food_ordering_system.common.mapper.ObjectMapper;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpRequestDto;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.enumerate.RestaurantStatus;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import com.jackson.food_ordering_system.resturant.service.RestaurantSignUpService;
import com.jackson.food_ordering_system.user.model.UserEntity;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * RestaurantSignUpServiceImpl Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
@RequiredArgsConstructor
public class RestaurantSignUpServiceImpl implements RestaurantSignUpService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantSignUpResponseDto registerRestaurant(RestaurantSignUpRequestDto requestDto) {

        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already used");
        }

        UserEntity owner = UserEntity.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getOwnerName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .phone(requestDto.getPhone())
                .role("RESTAURANT_OWNER")
                .build();
        userRepository.save(owner);

        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantName(requestDto.getRestaurantName())
                .restaurantAddress(requestDto.getRestaurantAddress())
                .cuisine(requestDto.getCuisine())
                .restaurantStatus(String.valueOf(RestaurantStatus.PENDING))
                .createdAt(LocalDateTime.now())
                .owner(owner)
                .build();

        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurant);
        return ObjectMapper.map(savedRestaurant, RestaurantSignUpResponseDto.class);
    }

}

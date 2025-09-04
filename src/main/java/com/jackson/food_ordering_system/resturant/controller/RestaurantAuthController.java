package com.jackson.food_ordering_system.resturant.controller;

import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpRequestDto;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.service.RestaurantSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantAuthController {

    private final RestaurantSignUpService restaurantSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<RestaurantSignUpResponseDto> restaurantSignUp(@RequestPart("restaurantSignUpData")RestaurantSignUpRequestDto requestDto){

        RestaurantSignUpResponseDto response = restaurantSignUpService.registerRestaurant(requestDto);

        return ResponseEntity.ok(response);

    }

}

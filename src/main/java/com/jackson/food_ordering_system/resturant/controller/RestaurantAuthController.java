package com.jackson.food_ordering_system.resturant.controller;

import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpRequestDto;
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

    @PostMapping("/signup")
    public ResponseEntity<?> restaurantSignUp(@RequestPart("restaurantSignUpData")RestaurantSignUpRequestDto requestDto){



    }

}

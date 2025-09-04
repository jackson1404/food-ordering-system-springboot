package com.jackson.food_ordering_system.resturant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantAuthController {

    @PostMapping("/signup")
    public ResponseEntity<?> restaurantSignUp(){



    }

}

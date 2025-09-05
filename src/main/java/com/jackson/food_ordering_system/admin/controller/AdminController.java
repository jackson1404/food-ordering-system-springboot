package com.jackson.food_ordering_system.admin.controller;

import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RestaurantService restaurantService;

    @GetMapping("/getRestaurant")
    public ResponseEntity<List<RestaurantSignUpResponseDto>> getRestaurantList(
            @RequestParam(value = "status", required = false) String status){
        return ResponseEntity.ok(restaurantService.getRestaurants(status));

    }

}

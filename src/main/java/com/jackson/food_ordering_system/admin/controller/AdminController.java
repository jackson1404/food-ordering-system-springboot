package com.jackson.food_ordering_system.admin.controller;

import com.jackson.food_ordering_system.admin.service.AdminRestaurantService;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RestaurantService restaurantService;
    private final AdminRestaurantService adminRestaurantService;

    @GetMapping("/getRestaurant")
    public ResponseEntity<List<RestaurantSignUpResponseDto>> getRestaurantList(
            @RequestParam(value = "status", required = false) String status){
        return ResponseEntity.ok(restaurantService.getRestaurants(status));

    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approveRestaurant(@PathVariable Long id,
                                                  @RequestParam(value = "approveMessage", required = false) String approveMessage) {
        adminRestaurantService.approveRestaurant(id, approveMessage);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> rejectRestaurant(@PathVariable Long id,
                                                 @RequestParam(value= "rejectMessage", required = false) String rejectMessage) {
        adminRestaurantService.rejectRestaurant(id, rejectMessage);
        return ResponseEntity.noContent().build();
    }



}

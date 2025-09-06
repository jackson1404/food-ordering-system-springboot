package com.jackson.food_ordering_system.resturant.controller;

import com.jackson.food_ordering_system.resturant.dto.StaffInviteRequestDto;
import com.jackson.food_ordering_system.resturant.service.RestaurantStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant-staff")
@RequiredArgsConstructor
public class RestaurantStaffController {

    private final RestaurantStaffService restaurantStaffService;

    @PostMapping("/{restaurantId}/invite")
    public ResponseEntity<Void> inviteStaff(
            @PathVariable Long restaurantId,
            @RequestBody StaffInviteRequestDto inviteRequestDto){

        restaurantStaffService.inviteStaff(restaurantId, inviteRequestDto);
        return ResponseEntity.ok().build();

    }




}

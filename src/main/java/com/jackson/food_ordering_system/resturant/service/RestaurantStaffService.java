package com.jackson.food_ordering_system.resturant.service;

import com.jackson.food_ordering_system.resturant.dto.StaffInviteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantStaffService {

    void inviteStaff(Long restaurantId, StaffInviteRequestDto inviteRequestDto);

}

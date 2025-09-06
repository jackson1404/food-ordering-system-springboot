package com.jackson.food_ordering_system.resturant.dto;

import lombok.Data;

@Data
public class StaffInviteRequestDto {
    private String email;
    private String name;
    private String role;
}

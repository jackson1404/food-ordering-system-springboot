package com.jackson.food_ordering_system.auth.dto;

import lombok.Data;

@Data
public class AuthRequestDto {

    private String username;
    private String password;

}

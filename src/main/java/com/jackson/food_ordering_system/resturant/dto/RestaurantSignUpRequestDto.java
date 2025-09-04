package com.jackson.food_ordering_system.resturant.dto;

import lombok.Data;

@Data
public class RestaurantSignUpRequestDto {

    private String ownerName;

    private String restaurantName;

    private String restaurantAddress;

    private String email;

    private String phone;

    private String password;


    private String cuisine;


}

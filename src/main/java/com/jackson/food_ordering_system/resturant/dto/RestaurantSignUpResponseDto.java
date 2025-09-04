/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.dto;

import lombok.Data;

/**
 * RestaurantSignUpResponseDto Class.
 * <p>
 * </p>
 *
 * @author
 */
@Data
public class RestaurantSignUpResponseDto {

    private Long id;
    private String restaurantName;
    private String restaurantAddress;
    private String cuisine;
    private String status;

}

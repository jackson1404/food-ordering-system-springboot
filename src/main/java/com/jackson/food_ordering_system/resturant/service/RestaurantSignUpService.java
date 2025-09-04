/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.service;

import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpRequestDto;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import org.springframework.stereotype.Service;

/**
 * RestaurantSignUpService Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
public interface RestaurantSignUpService {

     RestaurantSignUpResponseDto registerRestaurant(RestaurantSignUpRequestDto requestDto);



}

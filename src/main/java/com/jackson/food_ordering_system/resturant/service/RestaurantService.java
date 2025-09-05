/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.service;

import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RestaurantService Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
public interface RestaurantService {

    List<RestaurantEntity> findAll();

    List<RestaurantSignUpResponseDto> getRestaurants(String status);
}


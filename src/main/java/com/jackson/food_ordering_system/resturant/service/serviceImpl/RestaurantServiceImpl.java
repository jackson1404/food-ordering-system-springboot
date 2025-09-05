/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.service.serviceImpl;

import com.jackson.food_ordering_system.common.mapper.ObjectMapper;
import com.jackson.food_ordering_system.resturant.dto.RestaurantSignUpResponseDto;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import com.jackson.food_ordering_system.resturant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RestaurantServiceImpl Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantEntity> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<RestaurantSignUpResponseDto> getRestaurants(String status) {

        List<RestaurantEntity> restaurantEntities;

        if (status != null){
            restaurantEntities = restaurantRepository.findByRestaurantStatus(status);
        } else {
            restaurantEntities = restaurantRepository.findAll();
        }

        return ObjectMapper.map(restaurantEntities, RestaurantSignUpResponseDto.class);
    }
}

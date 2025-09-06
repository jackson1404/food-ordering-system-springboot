/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.admin.service.serviceImpl;

import com.jackson.food_ordering_system.admin.service.AdminRestaurantService;
import com.jackson.food_ordering_system.resturant.constants.RestaurantStatus;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AdminRestaurantServiceImpl Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
@RequiredArgsConstructor
public class AdminRestaurantServiceImpl implements AdminRestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public void approveRestaurant(Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new IllegalCallerException("Restaurant with {} not found" + id));

        if (restaurant.getRestaurantStatus().equals(RestaurantStatus.APPROVED)){
            throw new IllegalStateException("Restaurant already approved.");
        }

        restaurant.setRestaurantStatus(RestaurantStatus.APPROVED);
        restaurantRepository.save(restaurant);


    }

    @Override
    @Transactional
    public void rejectRestaurant(Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        if (restaurant.getRestaurantStatus().equals(RestaurantStatus.REJECTED)){
            throw new IllegalStateException("Restaurant already rejected");
        }

        restaurant.setRestaurantStatus(RestaurantStatus.REJECTED);
        restaurantRepository.save(restaurant);
    }
}

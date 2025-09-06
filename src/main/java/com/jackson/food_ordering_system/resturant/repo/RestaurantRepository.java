/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.repo;

import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * RestaurantRepository Class.
 * <p>
 * </p>
 *
 * @author
 */

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    List<RestaurantEntity> findByRestaurantStatus(String status);

    Optional<RestaurantEntity> findByRestaurantId(Long restaurantId);
}

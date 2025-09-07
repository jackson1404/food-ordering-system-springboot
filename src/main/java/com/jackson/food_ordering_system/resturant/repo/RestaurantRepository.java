/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.resturant.repo;

import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.user.model.UserEntity;
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
    Optional<RestaurantEntity> findByOwner(UserEntity user);
    Optional<RestaurantEntity> findByRestaurantId(Long restaurantId);
}

/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.admin.service.serviceImpl;

import com.jackson.food_ordering_system.admin.service.AdminRestaurantService;
import com.jackson.food_ordering_system.common.config.AppPropertiesConfig;
import com.jackson.food_ordering_system.common.service.EmailService;
import com.jackson.food_ordering_system.resturant.constants.RestaurantStatus;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private final EmailService emailService;
    private final AppPropertiesConfig appPropertiesConfig;

    @Override
    @Transactional
    public void approveRestaurant(Long id, String approvalMessage) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new IllegalCallerException("Restaurant with {} not found" + id));

        if (restaurant.getRestaurantStatus().equals(RestaurantStatus.APPROVED)){
            throw new IllegalStateException("Restaurant already approved.");
        }

        restaurant.setRestaurantStatus(RestaurantStatus.APPROVED);
        restaurantRepository.save(restaurant);

        Map<String, Object> model = new HashMap<>();
        model.put("ownerName", restaurant.getOwner().getUsername());
        model.put("restaurantName", restaurant.getRestaurantName());
        model.put("status", "APPROVED");
        model.put("message", approvalMessage);
        model.put("loginUrl", appPropertiesConfig.getFrontendBaseUrl() + "/login");
        model.put("resubmitUrl", appPropertiesConfig.getFrontendBaseUrl() + "/restaurant/signup");

        emailService.sendRestaurantApprovalEmail(restaurant.getOwner().getEmail(),
                "Your restaurant has been approved!",
                model);
    }

    @Override
    @Transactional
    public void rejectRestaurant(Long id, String rejectMessage) {
        RestaurantEntity restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        if (restaurant.getRestaurantStatus().equals(RestaurantStatus.REJECTED)){
            throw new IllegalStateException("Restaurant already rejected");
        }

        restaurant.setRestaurantStatus(RestaurantStatus.REJECTED);
        restaurantRepository.save(restaurant);

        Map<String, Object> model = new HashMap<>();
        model.put("ownerName", restaurant.getOwner().getUsername());
        model.put("restaurantName", restaurant.getRestaurantName());
        model.put("status", "REJECT");
        model.put("message", rejectMessage);
        model.put("resubmitUrl", appPropertiesConfig.getFrontendBaseUrl() + "/restaurant/signup");

        emailService.sendRestaurantApprovalEmail(restaurant.getOwner().getEmail(),
                "Your restaurant has been rejected!",
                model);

    }
}

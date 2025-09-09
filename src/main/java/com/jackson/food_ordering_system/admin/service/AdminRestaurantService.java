/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.admin.service;

import org.springframework.stereotype.Service;

/**
 * AdminRestaurantService Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
public interface AdminRestaurantService {

    void approveRestaurant(Long id, String approvalMessage);
    void rejectRestaurant(Long id, String rejectMessage);

}

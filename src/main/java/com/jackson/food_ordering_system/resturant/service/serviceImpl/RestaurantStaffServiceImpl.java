package com.jackson.food_ordering_system.resturant.service.serviceImpl;

import com.jackson.food_ordering_system.common.config.AppPropertiesConfig;
import com.jackson.food_ordering_system.resturant.dto.StaffInviteRequestDto;
import com.jackson.food_ordering_system.resturant.entity.InviteTokenEntity;
import com.jackson.food_ordering_system.resturant.entity.RestaurantEntity;
import com.jackson.food_ordering_system.resturant.repo.InviteTokenRepository;
import com.jackson.food_ordering_system.resturant.repo.RestaurantRepository;
import com.jackson.food_ordering_system.resturant.service.RestaurantStaffService;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantStaffServiceImpl implements RestaurantStaffService {

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private final InviteTokenRepository inviteTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AppPropertiesConfig appPropertiesConfig;

    @Override
    @Transactional
    public void inviteStaff(Long restaurantId, StaffInviteRequestDto inviteRequestDto) {

        if (userRepository.findByEmail(inviteRequestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email Already In Use");
        }

        RestaurantEntity restaurant = restaurantRepository.findByRestaurantId(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("Restaurant Not Found"));

        String token = UUID.randomUUID().toString();

        InviteTokenEntity invite = InviteTokenEntity.builder()
                .token(token)
                .email(inviteRequestDto.getEmail())
                .restaurant(restaurant)
                .expiry(LocalDateTime.now().plusDays(appPropertiesConfig.getTokenExpirationHours()))
                .build();
        inviteTokenRepository.save(invite);

        String inviteLink = String.format("%s/staff/setup?token=%s", appPropertiesConfig.getFrontendBaseUrl(), token);

    }


}

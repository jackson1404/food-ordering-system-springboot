package com.jackson.food_ordering_system.resturant.repo;

import com.jackson.food_ordering_system.resturant.entity.InviteTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteTokenRepository extends JpaRepository<InviteTokenEntity, Long> {
}

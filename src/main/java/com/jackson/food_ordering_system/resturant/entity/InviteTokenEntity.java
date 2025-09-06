package com.jackson.food_ordering_system.resturant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_invite_tokens")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InviteTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(nullable = false)
    private LocalDateTime expiry;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiry);
    }
}

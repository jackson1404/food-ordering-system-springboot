package com.jackson.food_ordering_system.user.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String role;

}

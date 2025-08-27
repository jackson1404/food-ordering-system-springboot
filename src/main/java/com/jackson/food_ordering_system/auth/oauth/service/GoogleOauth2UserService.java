/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.auth.oauth.service;

import com.jackson.food_ordering_system.user.model.UserEntity;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * GoogleOauth2UserService Class.
 * <p>
 * </p>
 *
 * @author
 */
@Service
@RequiredArgsConstructor
public class GoogleOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attrs = oAuth2User.getAttributes();
        String email = (String) attrs.get("email");
        String name = (String) attrs.getOrDefault("name", email);
        String picture = (String) attrs.getOrDefault("profile", null);

        userRepository.findByUsername(email).orElseGet(() -> {
            UserEntity user = new UserEntity();
            user.setUsername(email);
            user.setPassword(bCryptPasswordEncoder.encode("OAUTH2"));
            user.setRole("USER");
            return userRepository.save(user);
        });
        return oAuth2User;
    }
}

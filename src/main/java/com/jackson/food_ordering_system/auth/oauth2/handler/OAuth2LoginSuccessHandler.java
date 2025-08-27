/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.auth.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.food_ordering_system.auth.service.JwtService;
import com.jackson.food_ordering_system.user.model.UserEntity;
import com.jackson.food_ordering_system.user.repo.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * OAuth2LoginSuccessHandler Class.
 * <p>
 * </p>
 *
 * @author
 */
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${app.oauth2.redirect-success:}")
    private String redirectSuccess;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("name");

        UserEntity user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("OAuth2 User Not Found or Provisioned"));

        String role = user.getRole();
        String jwt = jwtService.generateToken(user.getUsername(), role);

        if (redirectSuccess != null && !redirectSuccess.isBlank()) {
            String url = redirectSuccess + "?token=" + URLEncoder.encode(jwt, StandardCharsets.UTF_8);
            response.sendRedirect(url);
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(Map.of("token", jwt)));

    }
}

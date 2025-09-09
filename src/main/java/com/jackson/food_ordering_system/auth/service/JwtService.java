package com.jackson.food_ordering_system.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String userName, String role){
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES)))
                .claim("role" , role)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUserName(String token) {
        return parseClaim(token).getSubject();
    }

    public String extractUserRole(String token){
        Object roleObj = parseClaim(token).get("role");
        return roleObj == null ? null : roleObj.toString();
    }

    public Claims parseClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenValid(String jwt) {
        try {
            parseClaim(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public boolean validateUserDetails(String token, UserDetails userDetails){
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        Date expiration = parseClaim(token).getExpiration();
        return expiration.before(new Date());
    }

}

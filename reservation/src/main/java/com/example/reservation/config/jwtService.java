package com.example.reservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.UUID;

@Service
public class jwtService {
    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseToken(String token) {
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();
        TokenInfo info = TokenInfo.builder()
                .id(UUID.fromString(body.get("id", String.class)))
                .name(body.get("name", String.class))
                .build();
        return info;
    }
}

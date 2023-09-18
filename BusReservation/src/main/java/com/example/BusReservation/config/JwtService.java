package com.example.BusReservation.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseToken(String token){
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();

        TokenInfo info = TokenInfo.builder()
                .id(body.get("id"))
    }
}

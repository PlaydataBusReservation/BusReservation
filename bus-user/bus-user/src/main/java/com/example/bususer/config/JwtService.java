package com.example.bususer.config;

import com.example.bususer.domain.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseToken(String token) {
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();
        TokenInfo tokenInfo = TokenInfo.builder()
                .id(UUID.fromString(body.get("id", String.class)))
                .username(body.get("username", String.class))
                .build();
//        if(!tokenInfo.getRole().equals("USER"))
//            throw new IllegalArgumentException("403");
        return tokenInfo;

    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parse(token)
                .getBody();

    }

    public TokenInfo extractUser(String token){
        Claims claims = extractAllClaims(token);
        return TokenInfo.builder()
                .id(UUID.fromString(claims.get("id", String.class)))
                .username(claims.get("username", String.class))
                .email(claims.get("email", String.class))
                .role(Role.valueOf(claims.get("role", String.class)))
                .build();
    }
}

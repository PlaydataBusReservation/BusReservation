package com.example.busgateway.config;

import org.springframework.stereotype.Service;

/*
 토큰 파싱에 실패하면 유효하지 않은 토큰으로 처리될 것
 */
@Service
public class JwtService {

    public Boolean parseToken(String token){
        try{
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
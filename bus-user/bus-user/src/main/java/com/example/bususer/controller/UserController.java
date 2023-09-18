package com.example.bususer.controller;

import com.example.bususer.config.JwtService;
import com.example.bususer.config.TokenInfo;
import com.example.bususer.domain.entity.User;
import com.example.bususer.domain.request.SignupRequest;
import com.example.bususer.domain.response.SignupResponse;
import com.example.bususer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bususer")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    // 토큰을 받아와 회원가입 여부를 확인하고 응답을 반환
//    @PostMapping("check")
//    public SignupResponse check(
//            @RequestHeader("Authorization") String token) {
//        TokenInfo tokenInfo = jwtService.parseToken(
//                token.replace("Bearer", ""));
//        return userService.checkSignup(tokenInfo);
//    }

    // 회원가입 정보를 받아와 저장
    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody SignupRequest signupRequest,
                     @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer ", ""));
        userService.save(signupRequest, tokenInfo);
    }


    // 현재 사용자의 정보를 조회
    @GetMapping("me")
    public User getMe(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer ", ""));
        return userService.getMe(tokenInfo);
    }


    // 회원가입을 처리하고 응답을 반환
    @PostMapping("check")
    public SignupResponse signup(
            @RequestBody SignupRequest signupRequest,
            @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer", ""));
        return userService.skipSignup(signupRequest, tokenInfo);
    }


    // 주어진 UUID에 해당하는 사용자 정보를 조회
    @GetMapping("{id}")
    public User getById(@PathVariable String uuid) {
        return userService.getById(UUID.fromString(uuid));
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
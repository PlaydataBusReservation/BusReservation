package com.example.bususer.service;

import com.example.bususer.client.DriverClient;
import com.example.bususer.client.ManagerClient;
import com.example.bususer.client.Request;
import com.example.bususer.config.TokenInfo;
import com.example.bususer.domain.entity.Role;
import com.example.bususer.domain.entity.User;
import com.example.bususer.domain.request.SignupRequest;
import com.example.bususer.domain.response.SignupResponse;
import com.example.bususer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final DriverClient driverClient;
    private final ManagerClient managerClient;
    // 사용자 회원가입 정보를 저장
    public void save(SignupRequest signupRequest, TokenInfo tokenInfo){
        System.out.println(tokenInfo.getUsername());
        UUID uuid = tokenInfo.getId();
        userRepository.save(signupRequest.toEntity(uuid));
        Request request = new Request(tokenInfo.getId(),tokenInfo.getUsername());
        if(signupRequest.getRole() == Role.DRIVER){

            ResponseEntity<Void> response = driverClient.saveDriver(request);
            if(response.getStatusCode() != HttpStatus.CREATED) {
                String err = signupRequest.getRole().name() + "-SERVICE DEAD";
                throw new RuntimeException(err);
            }

        }else if (signupRequest.getRole() == Role.MANAGER){
            ResponseEntity<Void> response1 = managerClient.saveManager(request);
            if (response1.getStatusCode() != HttpStatus.CREATED) {
                String err = signupRequest.getRole().name() + "-SERVICE DEAD";
                throw new RuntimeException(err);

            }

        }}



    // 사용자 회원가입 여부를 확인하고, 회원가입 상태에 따른 응답을 생성
    public SignupResponse checkSignup(TokenInfo tokenInfo){
        User user = getByToken(tokenInfo);
        return SignupResponse.builder()
                .user(user)
                .redirect(user.getAge()==null? "/signup" : "/main")
                .build();
    }
    @Transactional
    public SignupResponse skipSignup(SignupRequest signupRequest, TokenInfo tokenInfo) {
        User user = getByToken(tokenInfo);

        if (user != null) {
            return SignupResponse.builder()
                    .user(user)
                    .redirect("/main")
                    .build();
        } else {
            return SignupResponse.builder()
                    .redirect("/signup")
                    .build();
        }
    }

    // 현재 사용자의 정보를 조회
    public User getMe(TokenInfo tokenInfo) {
        return getById(tokenInfo.getId());
    }


    // 주어진 UUID에 해당하는 사용자를 조회
    // UUID에 해당하는 사용자가 없는 경우 예외 발생
    public User getById(UUID uuid){
        return userRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("NOT EXIST"));
    }


    // 토큰 정보를 기반으로 사용자를 조회
    // 토큰에 해당하는 사용자가 없는 경우 null 반환
    private User getByToken(TokenInfo tokenInfo){
        UUID uuid = tokenInfo.getId();
        Optional<User> byId = userRepository.findById(uuid);
        if (byId.isEmpty()){

            return null;
        }else {
            return byId.get();
        }
    }
}

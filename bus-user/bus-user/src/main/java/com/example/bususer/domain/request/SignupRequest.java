package com.example.bususer.domain.request;

import com.example.bususer.config.TokenInfo;
import com.example.bususer.domain.entity.Role;
import com.example.bususer.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignupRequest {
    private Integer age;
    private Role role;

    public User toEntity(UUID id){
        return User.builder()
                .id(id)
                .age(age)
                .role(role)
                .build();
    }
}

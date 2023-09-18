package com.example.bususer.domain.response;

import com.example.bususer.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private User user;
    private String redirect;
}

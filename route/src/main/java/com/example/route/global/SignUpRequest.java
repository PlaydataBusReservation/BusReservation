package com.example.route.global;

import com.example.route.driver.Driver;
import com.example.route.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpRequest {
    private UUID id;
    private String username;

    public Driver toDriver() {
        return Driver.builder()
                .id(id)
                .name(username)
                .build();
    }

    public Manager toManager() {
        return Manager.builder()
                .id(id)
                .name(username)
                .build();
    }
}

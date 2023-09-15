package com.example.route.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DriverRequest {
    private UUID id;
    private String username;

    public Driver toEntity() {
        return Driver.builder()
                .id(id)
                .name(username)
                .build();
    }
}

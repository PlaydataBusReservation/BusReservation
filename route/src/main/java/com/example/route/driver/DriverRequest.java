package com.example.route.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DriverRequest {
    private UUID id;
    private String name;

    public Driver toEntity() {
        return Driver.builder()
                .id(id)
                .name(name)
                .build();
    }
}

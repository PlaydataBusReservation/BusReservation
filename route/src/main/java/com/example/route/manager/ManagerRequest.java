package com.example.route.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ManagerRequest {
    private UUID id;
    private String name;

    public Manager toEntity(){
        return Manager.builder()
                .id(id)
                .name(name)
                .build();
    }
}

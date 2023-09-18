package com.example.bususer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("MANAGER-SERVICE")
public interface ManagerClient {
    @PostMapping("/api/v1/manager")
    ResponseEntity<Void> saveManager(Request request);

}
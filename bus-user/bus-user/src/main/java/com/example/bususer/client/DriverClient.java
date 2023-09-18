package com.example.bususer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("DRIVER-SERVICE")
public interface DriverClient {
    @PostMapping("/api/v1/driver")
    ResponseEntity<Void> saveDriver(Request request);

}
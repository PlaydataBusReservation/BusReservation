package com.example.route.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("BUS-SERVICE")
public interface BusClient {


    @PostMapping("/api/v1/reservation")
    ResponseEntity<Void> saveBus(BusRequest request);
}


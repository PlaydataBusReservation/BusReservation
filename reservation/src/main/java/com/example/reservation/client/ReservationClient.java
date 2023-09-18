package com.example.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("RESERVATION-SERVICE")
public interface ReservationClient {
    @PostMapping("/api/v1/reservation")
    ResponseEntity<Void> saveBus(BusRequest request);
}


package com.example.reservation.client;

import com.example.reservation.domain.request.BusSearchReqeust;
import com.example.reservation.domain.request.TicketRequest;
import com.example.reservation.domain.response.BusResponse;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("RESERVATION-SERVICE")
public interface ReservationClient {
    @PostMapping("/api/v1/reservation")
    ResponseEntity<Void> saveBus(BusRequest request);

    @PostMapping("/api/vi/reservation/search")
    ResponseEntity<List<BusResponse>> busSearch(BusSearchReqeust reqeust);

    @PostMapping("/api/v1/ticket")
    ResponseEntity<Void> createTicket(TicketRequest request);

    @GetMapping("/api/v1/ticket/findTicketExist")
    ResponseEntity<Boolean> findTicketExist (@RequestParam("busId") Long busId, @RequestParam("seatNumber") Integer seatNumber);
}


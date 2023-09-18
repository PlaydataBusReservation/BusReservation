package com.example.reservation.domain.request;

import com.example.reservation.domain.entity.Bus;
import com.example.reservation.domain.response.BusResponse;

import java.time.LocalDateTime;

public record SeatReservationRequest(Long busId, Integer seatNumber, String state) {
    public TicketRequest toTicketRequest(BusResponse bus){
        return TicketRequest.builder()
                .bus_id(busId)
                .departure_Date(LocalDateTime.parse(bus.getDeparture_Date().toString()))
                .departure_terminal(bus.getDeparture_terminal())
                .destination_terminal(bus.getDestination_terminal())
                .seat_number(seatNumber)
                .build();
    }
}

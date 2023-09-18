package com.example.reservation.domain.request;

public record SeatReservationRequest(Long busId, Integer seatNumber, String state) {
}

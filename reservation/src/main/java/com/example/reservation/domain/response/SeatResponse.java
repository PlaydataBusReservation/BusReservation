package com.example.reservation.domain.response;

import com.example.reservation.domain.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SeatResponse {
    private Integer seatNumber;
    private String field;

    public SeatResponse (Seat seat){
        this.seatNumber = seat.getSeatNumber();
        this.field = seat.getField();
    }
}

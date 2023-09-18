package com.example.reservation.domain.response;

import com.example.reservation.config.SeatState;
import com.example.reservation.domain.entity.Bus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class BusResponse {
    private Long id;
    private Date departure_Date;
    private String seat_type;
    private String departure_terminal;
    private String destination_terminal;
    private UUID driver_id;
    private int maxSeatCnt;
    private int nowReservationSeatCnt;

    public BusResponse (Bus bus){
        this.id = bus.getId();
        this.departure_Date = bus.getDeparture_Date();
        this.seat_type = bus.getSeat_type();
        this.departure_terminal = bus.getDeparture_terminal();
        this.destination_terminal = bus.getDestination_terminal();
        this.driver_id = bus.getDriver_id();
        this.maxSeatCnt = bus.getSeatList().size();
        this.nowReservationSeatCnt = bus.getSeatList().stream()
                .filter(seat -> seat.getField() != SeatState.EMPTY.name()).toList().size();
    }
}

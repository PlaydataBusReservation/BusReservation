package com.example.reservation.domain.request;

import com.example.reservation.config.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder @Getter
public record TicketRequest(
        LocalDateTime departure_Date,
        String departure_terminal,
        String destination_terminal,
        Integer seat_number,
        Long bus_id,
        State state
){

}

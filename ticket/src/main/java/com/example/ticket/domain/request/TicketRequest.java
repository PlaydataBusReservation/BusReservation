package com.example.ticket.domain.request;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.entity.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;


public record TicketRequest(
     LocalDateTime departure_Date,
     String departure_terminal,
     String destination_terminal,
     Integer seat_number,
     Long bus_id,
     State state
){
    public Ticket toEntity(UUID userId){
        return Ticket.builder()
                .departure_Date(departure_Date)
                .departure_terminal(departure_terminal)
                .destination_terminal(destination_terminal)
                .seat_number(seat_number)
                .user_id(userId)
                .bus_id(bus_id)
                .state(state)
//                .owner(Owner.builder().id(ownerId).build())
                .build();
    }
}
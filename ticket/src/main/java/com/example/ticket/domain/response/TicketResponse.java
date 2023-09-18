package com.example.ticket.domain.response;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.entity.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketResponse {
    private Long ticket_Id;
    private String departure_terminal;
    private String destination_terminal;
    private LocalDateTime departure_Date;
    private Integer seat_number;
    private Long bus_id;
    @Enumerated(EnumType.STRING)
    private State state;

    public TicketResponse(Ticket ticket){
        this.ticket_Id = ticket.getId();
        this.departure_terminal = ticket.getDeparture_terminal();
        this.destination_terminal = ticket.getDestination_terminal();
        this.departure_Date = ticket.getDeparture_Date();
        this.seat_number = ticket.getSeat_number();
        this.bus_id = ticket.getBus_id();
        this.state = ticket.getState();

    }
}

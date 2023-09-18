package com.example.ticket.domain.request;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TicketUpdateRequest {
    private State state;
    private Long ticketId;
}

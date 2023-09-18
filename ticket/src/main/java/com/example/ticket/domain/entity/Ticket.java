package com.example.ticket.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "Tickets")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departure_terminal;
    private String destination_terminal;
    private LocalDateTime departure_Date;
    private Integer seat_number;
    private UUID user_id;
    private Long bus_id;
    @Enumerated(EnumType.STRING)
    private State state;

//    @ManyToOne
//    private User user;

}

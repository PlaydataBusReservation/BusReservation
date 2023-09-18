package com.example.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Driver;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Bus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date departure_Date;
    private String seat_type;
    private String departure_terminal;
    private String destination_terminal;
    private UUID driver_id;
    @OneToMany(mappedBy = "bus")
    private List<Seat> seatList;
}

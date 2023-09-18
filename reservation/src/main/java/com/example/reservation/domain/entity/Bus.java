package com.example.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Driver;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Bus {
    @Id
    private Long id;
    private Date departure_Date;
    private String seat_type;
    private Long route_id;
    private Long driver_id;
    @OneToMany(mappedBy = "bus")
    private List<Seat> seatList;
}

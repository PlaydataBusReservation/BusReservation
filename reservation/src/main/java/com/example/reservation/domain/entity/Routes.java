package com.example.reservation.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "routes")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Routes {
    @Id
    private Long id;
    private String departure_terminal;
    private String destination_terminal;
    private Long bus_id;
}

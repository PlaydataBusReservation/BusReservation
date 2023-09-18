package com.example.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Seat {
    @Id
    private Long id;
    private Integer seatNumber;
    private String field;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bus bus;

    public void setField(String field) {
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public String getField() {
        return field;
    }

    public Bus getBus() {
        return bus;
    }
}

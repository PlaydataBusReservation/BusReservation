package com.example.reservation.client;

import com.example.reservation.domain.entity.Bus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {
    private Date departureDate;
    private String seatType;
    private String departureTerminal;
    private String destinationTerminal;
    private UUID driverId;

    public Bus toEntity(BusRequest request)
    {
        return Bus.builder()
                .departure_Date(departureDate)
                .seat_type(seatType)
                .driver_id(driverId)
                .departure_terminal(departureTerminal)
                .destination_terminal(destinationTerminal)
                .build();
    }
}

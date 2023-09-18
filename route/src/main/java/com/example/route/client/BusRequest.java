package com.example.route.client;

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

    @Override
    public String toString() {
        return "BusRequest{" +
                "departureDate=" + departureDate +
                ", seatType='" + seatType + '\'' +
                ", departureTerminal='" + departureTerminal + '\'' +
                ", destinationTerminal='" + destinationTerminal + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}


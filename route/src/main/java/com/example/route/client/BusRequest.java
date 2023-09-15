package com.example.route.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {
    private Date departureDate;
    private String seatType;
    private Long routeId;
    private Long driverId;
}

package com.example.reservation.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
public class BusSearchReqeust {
    private Date departureDate;
    private String departureTerminal;
    private String destinationTerminal;
}

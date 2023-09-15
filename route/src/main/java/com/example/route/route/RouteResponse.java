package com.example.route.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RouteResponse {
    private Long id;
    //    private String departure_terminal;
    //    private String destination_terminal;
    private String terminalName;
    private String terminalCode;

    public RouteResponse(Route route) {
        this.id = route.getId();
        this.terminalName = route.getDestinationTerminal().getTerminalName();
        this.terminalCode = route.getDestinationTerminal().getTerminalCode();
    }
}

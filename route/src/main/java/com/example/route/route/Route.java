package com.example.route.route;


import com.example.route.terminal.Terminal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_terminal")
    private Terminal departureTerminal;

    @ManyToOne
    @JoinColumn(name = "destination_terminal")
    private Terminal destinationTerminal;

    public Route(Terminal departureTerminal, Terminal destinationTerminal) {
        this.departureTerminal = departureTerminal;
        this.destinationTerminal = destinationTerminal;
    }
}

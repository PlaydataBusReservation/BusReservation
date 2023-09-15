package com.example.route.route;


import com.example.route.terminal.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    boolean existsByDepartureTerminalAndDestinationTerminal(Terminal a, Terminal b);

    List<Route> findAllByDepartureTerminal(Terminal terminal);
}

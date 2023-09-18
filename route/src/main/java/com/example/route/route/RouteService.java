package com.example.route.route;


import com.example.route.terminal.Terminal;
import com.example.route.terminal.TerminalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository repository;
    private final TerminalRepository repository2;
    public List<RouteResponse> getRoute(RouteRequest request) {

        Terminal terminal = repository2.findById(request.getTerminalId())
                .orElseThrow(() -> new EntityNotFoundException("Terminal not found"));

        return repository.findAllByDepartureTerminal(terminal)
                .stream()
                .map(RouteResponse::new)
                .toList();
    }
}

package com.example.route.terminal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminalService {

    private final TerminalRepository repository;
    public List<TerminalResponse> getTerminal() {
        return repository.findAll()
                .stream()
                .map(TerminalResponse::new)
                .toList();
    }
}

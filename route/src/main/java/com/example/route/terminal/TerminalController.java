package com.example.route.terminal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terminal")
public class TerminalController {

    private final TerminalService terminalService;

    @GetMapping
    public List<TerminalResponse> getTerminal(){
        return terminalService.getTerminal();
    }


}

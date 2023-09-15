package com.example.route.terminal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TerminalResponse {
    private Long id;
    private String terminalName;
    private String terminalCode;

    public TerminalResponse(Terminal terminal) {
        this.id = terminal.getId();
        this.terminalName = terminal.getTerminalName();
        this.terminalCode = terminal.getTerminalCode();
    }
}

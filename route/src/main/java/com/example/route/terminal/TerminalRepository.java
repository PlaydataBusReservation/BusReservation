package com.example.route.terminal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository
        extends JpaRepository<Terminal, Long> {
    Terminal findByTerminalCode(String terminalCode);

    Terminal findByTerminalName(String terminalNm);


    boolean existsByTerminalName(String terminalNm);
}

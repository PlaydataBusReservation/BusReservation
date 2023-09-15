package com.example.route.terminal;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "terminal_name",columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin")
    private String terminalName;

    @Column(name = "terminal_code",columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin")
    private String terminalCode;

    public Terminal(String terminalName, String terminalCode) {
        this.terminalName = terminalName;
        this.terminalCode = terminalCode;
    }

    public Terminal(String terminalName) {
        this.terminalName = terminalName;
    }
}

package com.example.route.driver;

import com.example.route.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Driver {

    @Id
    private UUID id;

    private String name;

    @ManyToOne
    private Company company;

}

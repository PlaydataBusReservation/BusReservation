package com.example.route.manager;


import com.example.route.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Manager {

    @Id
    private UUID id;

    private String name;

    @ManyToOne
    private Company company;

}

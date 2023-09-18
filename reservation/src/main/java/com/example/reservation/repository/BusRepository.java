package com.example.reservation.repository;

import com.example.reservation.domain.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    public Optional<List<Bus>> getBydeparture_terminalAnddestination_terminalAnddeparture_Date(String departure_terminal, String destinationTerminal, Date departure_Date);
}

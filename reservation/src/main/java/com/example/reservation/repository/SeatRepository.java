package com.example.reservation.repository;

import com.example.reservation.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    public Optional<List<Seat>> getByBusidAndTerminalId(Long busId, Long terminalId);

    public Optional<Seat> getByBusidAndSeatNumber(Long busId, Integer seatNumber);
}

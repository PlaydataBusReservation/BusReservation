package com.example.reservation.service;

import com.example.reservation.domain.entity.Seat;
import com.example.reservation.domain.request.SeatRequest;
import com.example.reservation.domain.request.SeatReservationRequest;
import com.example.reservation.domain.response.SeatResponse;
import com.example.reservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ReservationService {
   private final SeatRepository seatRepository;

    public ReservationService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatResponse> getBusSeatInfo(SeatRequest request){
       Optional<List<Seat>> optional =
               seatRepository.getByBusidAndTerminalId(request.busId(), request.terminalId());

       return optional.get().stream()
               .map(SeatResponse::new).toList();
    }

    public void updateSeatState(SeatReservationRequest request){
        Optional<Seat> Optional = seatRepository.getByBusidAndSeatNumber(request.busId(), request.seatNumber());

        Seat seat = Optional.orElseThrow(() -> new IllegalArgumentException("not found seat"));

        seat.setField(request.state());
        seatRepository.save(seat);
    }
}

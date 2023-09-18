package com.example.reservation.service;

import com.example.reservation.client.BusRequest;
import com.example.reservation.config.SeatState;
import com.example.reservation.config.SeatType;
import com.example.reservation.domain.entity.Bus;
import com.example.reservation.domain.entity.Seat;
import com.example.reservation.domain.request.BusSearchReqeust;
import com.example.reservation.domain.request.SeatRequest;
import com.example.reservation.domain.request.SeatReservationRequest;
import com.example.reservation.domain.response.BusResponse;
import com.example.reservation.domain.response.SeatResponse;
import com.example.reservation.repository.BusRepository;
import com.example.reservation.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
   private final SeatRepository seatRepository;
   private final BusRepository busRepository;

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

    @Transactional
    public void saveBus(BusRequest request){
        Bus bus = request.toEntity(request);
        busRepository.save(bus);
        saveSeat(SeatType.valueOf(request.getSeatType()), bus);
    }

    @Transactional
    private void saveSeat(SeatType type, Bus bus){
        int loopCount = type == SeatType.TYPE_A ? 30 : 45;

        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < loopCount; ++i) {
            Seat s = Seat.builder()
                    .bus(bus)
                    .field(SeatState.EMPTY.name())
                    .seatNumber(i +1)
                    .build();
        }
        seatRepository.saveAll(seats);
    }

    public List<BusResponse> busSearch(BusSearchReqeust reqeust){
        Optional<List<Bus>> busListOptional = busRepository.getBydeparture_terminalAnddestination_terminalAnddeparture_Date(
                reqeust.getDepartureTerminal(), reqeust.getDepartureTerminal(), reqeust.getDepartureDate());

        List<Bus> busList = busListOptional.orElseThrow(() -> new IllegalArgumentException("Not Found Bus"));

        return busList.stream().map(BusResponse::new).toList();
    }

    public BusResponse findById(Long id){
        Optional<Bus> optional = busRepository.findById(id);
        Bus bus = optional.orElseThrow(() -> new IllegalArgumentException("Not Found Bus"));
        return new BusResponse(bus);
    }

}

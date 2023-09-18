package com.example.ticket.service;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.entity.Ticket;
import com.example.ticket.domain.request.TicketRequest;
import com.example.ticket.domain.response.TicketResponse;
import com.example.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    // 티켓 생성
    public void createTicket(TicketRequest ticketRequest, UUID userId){
        Ticket ticket = ticketRequest.toEntity(userId);
        ticketRepository.save(ticket);
    }

    // 티켓 조회
    public List<TicketResponse> findTicketsByUserId(UUID userId) {
        List<Ticket> tickets = ticketRepository.findTicketsByUserId(userId);
        return tickets.stream()
                .map(TicketResponse::new)
                .collect(Collectors.toList());
    }

    // 결제,삭제했을때 티켓 결제상태 업데이트
    public void ticketStateUpdate(UUID userId, Long ticketId, State newState){
        ticketRepository.ticketStateUpdate(userId, ticketId, newState);
    }

    // 예약취소 티켓 조회
    public List<TicketResponse> findCancelTicketsByUserId(UUID userId, State cancel) {
        List<Ticket> tickets = ticketRepository.findCancelTicketsByUserId(userId, cancel);
        return tickets.stream()
                .map(TicketResponse::new)
                .collect(Collectors.toList());
    }

//    // 예약취소 했을때 티켓 삭제
//    public void ticketDelete(Long ticketId){
//        ticketRepository.deleteById(ticketId);
//    }

    // 예약할때 해당 좌석 티켓없는지 확인하는 로직
    public boolean findTicketExist(Long busId, Integer seatNumber) {
        Optional<Ticket> ticketExist = ticketRepository.findTicketExist(busId, seatNumber);
        return ticketExist.isPresent();
    }


}

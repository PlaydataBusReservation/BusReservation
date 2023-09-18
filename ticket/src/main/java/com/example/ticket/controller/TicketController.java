package com.example.ticket.controller;

import com.example.ticket.domain.entity.State;
import com.example.ticket.domain.request.TicketRequest;
import com.example.ticket.domain.request.TicketUpdateRequest;
import com.example.ticket.domain.response.TicketResponse;
import com.example.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
@CrossOrigin(origins = "*")
public class TicketController {
    private final TicketService ticketService;

    // 티켓 생성
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(
            @RequestBody TicketRequest ticketRequest,
            @PathVariable UUID userId
//            토큰 만들면 토큰으로 교체
    ){
        UUID userId1 = UUID.fromString("c71c9328-a5ef-4e2a-9df0-1cc2d6eb3e85");
//        UUID userId1 = UUID.fromString("4f9ff8f0-c612-40a0-8f9d-396529da2fb3");

//        토큰 만들면 토큰으로 교체
        ticketService.createTicket(ticketRequest, userId1);
    }

    // 티켓 조회
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponse> findTicketsByUserId(
            @PathVariable UUID userId
//            토큰 만들면 토큰으로 교체
    ){
        UUID userId1 = UUID.fromString("c71c9328-a5ef-4e2a-9df0-1cc2d6eb3e85");
//      토큰 만들면 토큰으로 교체
        List<TicketResponse> ticketsByUserId = ticketService.findTicketsByUserId(userId1);
        return ticketsByUserId;
    }

    // 결제,삭제했을때 티켓 결제상태 업데이트
    @PutMapping("/{userId}")
    public void ticketStateUpdate(
//            @RequestBody TicketUpdateRequest ticketUpdateRequest
            @RequestParam("state") String state,
            @RequestParam("ticketId") Long ticketId
    ){
        UUID userId1 = UUID.fromString("c71c9328-a5ef-4e2a-9df0-1cc2d6eb3e85");
        State state1 = State.valueOf(state);
        ticketService.ticketStateUpdate(userId1, ticketId, state1);
//        ticketService.ticketStateUpdate(userId1
//                , ticketUpdateRequest.getTicketId()
//                , ticketUpdateRequest.getState());
    }

    // 예약취소 티켓 조회
    @GetMapping("/cancelTicket/{userId}")
    public List<TicketResponse> findCancelTicketsByUserId(
        @RequestParam("state") String state
    ){
        UUID userId1 = UUID.fromString("c71c9328-a5ef-4e2a-9df0-1cc2d6eb3e85");
        State state1 = State.valueOf(state);
        List<TicketResponse> cancelTicketsByUserId = ticketService.findCancelTicketsByUserId(userId1, state1);
        return cancelTicketsByUserId;
    }

    // 예약할때 해당 좌석 티켓없는지 확인하는 로직
    @GetMapping("/findTicketExist")
    public boolean findTicketExist(
            @RequestParam("busId") Long busId
            ,@RequestParam("seatNumber") Integer seatNumber){
        boolean ticketExist = ticketService.findTicketExist(busId, seatNumber);
        return ticketExist;
    }

}

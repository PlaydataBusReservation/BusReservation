package com.example.reservation.contorller;

import com.example.reservation.domain.request.SeatRequest;
import com.example.reservation.domain.request.SeatReservationRequest;
import com.example.reservation.domain.response.SeatResponse;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/Reservation")
public class ReservationController {
    private final ReservationService reservationService;

    //서버에서 클라로 좌석 상태 실시간 통지해주기위해 컨트롤러 가져옴
    private final WebSocketController webSocketController;

    public ReservationController(ReservationService reservationService, WebSocketController webSocketController) {
        this.reservationService = reservationService;
        this.webSocketController = webSocketController;
    }

    @GetMapping("/seat")
    public List<SeatResponse> getSeatState(@RequestParam Long busId, @RequestParam Long terminalId){
        //토큰 유효성 검사 추가할것

        //버스 좌석 리스트 조회
        return reservationService.getBusSeatInfo(new SeatRequest(busId, terminalId));
    }

    @PostMapping("/Reservation")
    public void updateSeatReservaiton(@RequestBody SeatReservationRequest request){

        //티켓 서버에 요청 후 티켓 생성 시 좌석 상태 변경

        //티켓 상테 변경
        reservationService.updateSeatState(request);

        webSocketController.sendMessage(request.busId().toString(),
                request.seatNumber() + "," + request.state());
    }
}

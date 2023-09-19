package com.example.reservation.contorller;

import com.example.reservation.client.BusRequest;
import com.example.reservation.client.ReservationClient;
import com.example.reservation.domain.request.BusSearchReqeust;
import com.example.reservation.domain.request.SeatRequest;
import com.example.reservation.domain.request.SeatReservationRequest;
import com.example.reservation.domain.response.BusResponse;
import com.example.reservation.domain.response.SeatResponse;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationClient feignClient;

    //서버에서 클라로 좌석 상태 실시간 통지해주기위해 컨트롤러 가져옴
    private final WebSocketController webSocketController;

    @GetMapping("/seat")
    public List<SeatResponse> getSeatState(@RequestParam Long busId){
        //토큰 유효성 검사 추가할것

        //버스 좌석 리스트 조회
        return reservationService.getBusSeatInfo(busId);
    }

    @PostMapping("/reservation")
    public void updateSeatReservaiton(@RequestBody SeatReservationRequest request){

        ResponseEntity<Boolean> response = feignClient.findTicketExist(request.busId(), request.seatNumber());
        if (response.getBody()) {
            //티켓 서버에 요청 후 티켓 생성 시 좌석 상태 변경
            feignClient.createTicket(request.toTicketRequest(reservationService.findById(request.busId())));

            //티켓 상테 변경
            reservationService.updateSeatState(request);

            webSocketController.sendMessage(request.busId().toString(),
                    request.seatNumber() + "," + request.state());
        }
    }

    @PostMapping
    public void saveBus(BusRequest request){
        reservationService.saveBus(request);
    }

    @PostMapping("/search")
    public List<BusResponse> busSearch (@RequestBody BusSearchReqeust reqeust){
        return reservationService.busSearch(reqeust);
    }
}

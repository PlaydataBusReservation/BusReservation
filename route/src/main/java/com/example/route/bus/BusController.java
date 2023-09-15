package com.example.route.bus;


import com.example.route.client.BusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bus")
public class BusController {
    private BusService busService;

    @GetMapping
    @PostMapping
    public void busSave(BusRequest request){
        busService.save(request);
    }

}

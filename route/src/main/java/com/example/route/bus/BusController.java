package com.example.route.bus;


import com.example.route.client.BusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver/bus")
public class BusController {
    private final BusService busService;


    @PostMapping
    public void busSave(@RequestBody BusRequest request){
        busService.save(request);
    }

}

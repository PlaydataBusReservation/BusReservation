package com.example.route.bus;


import com.example.route.client.BusClient;
import com.example.route.client.BusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusClient busClient;

    public void save(BusRequest request) {
        System.out.println(request);
        busClient.saveBus(request);
    }
}

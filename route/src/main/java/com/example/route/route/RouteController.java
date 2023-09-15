package com.example.route.route;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/route")
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public List<RouteResponse> getRoute(@RequestBody RouteRequest request){
        return routeService.getRoute(request);
    }

}
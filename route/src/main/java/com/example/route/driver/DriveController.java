package com.example.route.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver")
public class DriveController {
    private final DriverService driverService;

    @GetMapping
    public List<Driver> getDriver(){
        return driverService.getDriver();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDriver(@RequestBody DriverRequest request){
        driverService.saveDriver(request);
    }

}

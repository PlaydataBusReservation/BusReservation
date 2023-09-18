package com.example.route.driver;

import com.example.route.global.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getDriver() {
        return driverRepository.findAll();
    }

    public void saveDriver(SignUpRequest request) {
        driverRepository.save(request.toDriver());
    }


}

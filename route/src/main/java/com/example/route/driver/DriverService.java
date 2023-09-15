package com.example.route.driver;

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

    public void saveDriver(DriverRequest request) {
        driverRepository.save(request.toEntity());
    }


}

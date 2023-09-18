package com.example.route.manager;

import com.example.route.global.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver/manage")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveManger(@RequestBody SignUpRequest request){
        managerService.saveManager(request);
    }

}

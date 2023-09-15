package com.example.route.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manage")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping
    public void saveManger(ManagerRequest request){
        managerService.saveManager(request);
    }

}

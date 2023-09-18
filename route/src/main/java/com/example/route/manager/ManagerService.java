package com.example.route.manager;


import com.example.route.global.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public void saveManager(SignUpRequest request) {
        managerRepository.save(request.toManager());
    }
}

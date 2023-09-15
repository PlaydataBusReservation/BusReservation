package com.example.route.manager;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public void saveManager(ManagerRequest request) {
        managerRepository.save(request.toEntity());
    }
}

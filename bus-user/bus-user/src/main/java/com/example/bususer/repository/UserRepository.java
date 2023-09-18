package com.example.bususer.repository;

import com.example.bususer.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository
        extends JpaRepository<User, UUID> {
}

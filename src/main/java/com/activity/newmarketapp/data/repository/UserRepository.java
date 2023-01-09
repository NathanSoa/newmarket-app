package com.activity.newmarketapp.data.repository;

import com.activity.newmarketapp.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

package com.netflix.netflixb.repository;

import com.netflix.netflixb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Aşağıdaki satırı ekleyin:
    boolean existsByEmail(String email);
}

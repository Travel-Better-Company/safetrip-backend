package com.safetripbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safetripbackend.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Optional<Users> findByEmail(String email);

}

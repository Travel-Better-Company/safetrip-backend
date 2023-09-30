package com.safetripbackend.subscription.domain.persistence;

import com.safetripbackend.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
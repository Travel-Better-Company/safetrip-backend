package com.safetripbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safetripbackend.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}

package com.safetripbackend.followers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safetripbackend.followers.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}

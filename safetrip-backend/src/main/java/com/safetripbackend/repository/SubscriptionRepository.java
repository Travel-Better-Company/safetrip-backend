package com.safetripbackend.repository;

import com.safetripbackend.entity.Subscription;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
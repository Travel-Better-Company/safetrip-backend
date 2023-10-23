package com.safetripbackend.subscription.domain.service;

import com.safetripbackend.subscription.domain.entity.subscription;
import com.safetripbackend.subscription.domain.persitence.subscription_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSubscription {
    @Autowired
    private subscription_repository subscriptionRepository;

    public subscription createSubscription(subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public List<subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.delete(id);
    }

    public subscription updateSubscription(subscription updatedSubscription) {
        return updatedSubscription;
    }
}
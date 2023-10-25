package com.safetripbackend.service;


import com.safetripbackend.entity.Subscription;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) throws ResourceAlreadyExistsException {
        Subscription existingSubscription = subscriptionRepository.findByUserId(subscription.getUserId());
        if (existingSubscription != null) {
            throw new ResourceAlreadyExistsException("La suscripción ya existe para el usuario: " + subscription.getUserId());
        }

        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.delete(id);
    }

    public Subscription updateSubscription(Subscription updatedSubscription) {
        return updatedSubscription;
    }
}
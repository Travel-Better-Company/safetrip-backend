package com.safetripbackend.service;

import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.entity.Subscription;
import com.safetripbackend.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean existsSubscription(Long subscriptionId) {
        return subscriptionRepository.existsById(subscriptionId);
    }
    public SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO requestDTO) {
        Subscription subscription = new Subscription();
        subscription.setUser(userRepository.findById(requestDTO.getUserId()).orElse(null));
        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToResponseDTO(savedSubscription);
    }

    public SubscriptionResponseDTO getSubscriptionById(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (subscription != null) {
            return mapToResponseDTO(subscription);
        }
        return null;
    }

    public List<SubscriptionResponseDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private SubscriptionResponseDTO mapToResponseDTO(Subscription subscription) {
        SubscriptionResponseDTO responseDTO = new SubscriptionResponseDTO();
        responseDTO.setId(subscription.getId());
        responseDTO.setUserId(subscription.getUser().getId());
        responseDTO.setStartDate(subscription.getStartDate());
        responseDTO.setEndDate(subscription.getEndDate());
        return responseDTO;
    }
}
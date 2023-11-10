package com.safetripbackend.service;

import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.entity.Users;
import com.safetripbackend.entity.Subscription;
import com.safetripbackend.repository.SubscriptionRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
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

        // Proceed with creating a new subscription
        Users user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario con este ID: " + requestDTO.getUserId()));
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());
        //subscriptionRepository.save(subscription);
        //Subscription savedSubscription = subscriptionRepository.save(subscription);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToResponseDTO(savedSubscription);
    }

    public SubscriptionResponseDTO getSubscriptionById(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la suscripcion con este ID: " + subscriptionId));
        return mapToResponseDTO(subscription);
    }

    public List<SubscriptionResponseDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public  void deleteSubscription(long subs_id){
        if (!subscriptionRepository.existsById(subs_id)) {
            //Solución temporal
            throw new ResourceNotFoundException("No se encontro la suscripcion con este ID: "+subs_id);
            //Se tendría que crear una una excepcion en caso de que el objeto no exista
        }
        subscriptionRepository.deleteById(subs_id);
    }

    private SubscriptionResponseDTO mapToResponseDTO(Subscription subscription) {
        SubscriptionResponseDTO responseDTO = new SubscriptionResponseDTO();
        responseDTO.setId(subscription.getId());
        Users user_a = subscription.getUser();
        responseDTO.setUser_register(user_a);
        responseDTO.setStartDate(subscription.getStartDate());
        responseDTO.setEndDate(subscription.getEndDate());
        return responseDTO;
    }
}
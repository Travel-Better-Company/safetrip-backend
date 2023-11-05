package com.safetripbackend.controller;

import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> createSubscription(@RequestBody @Valid SubscriptionRequestDTO requestDTO) {
        try {
            SubscriptionResponseDTO responseDTO = subscriptionService.createSubscription(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> getSubscription(@PathVariable Long id) {
        SubscriptionResponseDTO responseDTO = subscriptionService.getSubscriptionById(id);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDTO>> getAllSubscriptions() {
        List<SubscriptionResponseDTO> responseDTOs = subscriptionService.getAllSubscriptions();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

}
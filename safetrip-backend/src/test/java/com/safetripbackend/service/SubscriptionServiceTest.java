package com.safetripbackend.service;
import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.exception.ValidationExpection;
import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.entity.Subscription;
import com.safetripbackend.entity.Users;
import com.safetripbackend.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionServiceTest {

    @InjectMocks
    private SubscriptionService subscriptionService;
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    ///US: Sucripción de itinerario -> Escenario Exitoso
    @Test
    public void testCreateSubscription() {
        SubscriptionRequestDTO requestDTO = new SubscriptionRequestDTO();
        requestDTO.setUserId(2L);
        requestDTO.setStartDate(LocalDate.now());
        requestDTO.setEndDate(LocalDate.now().plusDays(30));

        Subscription subscription = new Subscription();
        Users user = new Users();
        user.setId(requestDTO.getUserId());

        subscription.setUser(user);
        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());

        when(userRepository.findById(requestDTO.getUserId())).thenReturn(Optional.of(user));

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        SubscriptionResponseDTO responseDTO = subscriptionService.createSubscription(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(requestDTO.getUserId(), responseDTO.getUser_register().getId());
        assertEquals(requestDTO.getStartDate(), responseDTO.getStartDate());
        assertEquals(requestDTO.getEndDate(), responseDTO.getEndDate());
    }
    ///US: Sucripción de itinerario -> Escenario Alternativo
    @Test
    public void testCreateSubscriptionUserNotFound() {
        SubscriptionRequestDTO requestDTO = new SubscriptionRequestDTO();
        requestDTO.setUserId(-561L);
        requestDTO.setStartDate(LocalDate.now());
        requestDTO.setEndDate(LocalDate.now().plusDays(30));

        Subscription subscription = new Subscription();
        Users user = new Users();
        user.setId(requestDTO.getUserId());

        subscription.setUser(user);
        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> subscriptionService.createSubscription(requestDTO));
        assertEquals("No se encontro el usuario con este ID: " + requestDTO.getUserId(), exception.getMessage());
    }

}
package com.safetripbackend.service;
import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
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

    @Test
    public void testCreateSubscription() {
        SubscriptionRequestDTO requestDTO = new SubscriptionRequestDTO();
        requestDTO.setUserId(3L);
        requestDTO.setStartDate(LocalDate.now());
        requestDTO.setEndDate(LocalDate.now().plusDays(30));

        Subscription subscription = new Subscription();
        subscription.setUser(new Users());
        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());

        when(userRepository.findById(requestDTO.getUserId())).thenReturn(java.util.Optional.of(new Users()));

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        SubscriptionResponseDTO responseDTO = subscriptionService.createSubscription(requestDTO);

        assertEquals(requestDTO.getStartDate(), responseDTO.getStartDate());
        assertEquals(requestDTO.getEndDate(), responseDTO.getEndDate());
    }

}
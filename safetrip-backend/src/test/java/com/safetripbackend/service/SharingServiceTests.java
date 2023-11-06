package com.safetripbackend.service;
import com.safetripbackend.dto.SharingRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyLong;

import com.safetripbackend.service.ItineraryService;
import com.safetripbackend.controller.ItineraryController;
import com.safetripbackend.dto.SharingResponseDto;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class SharingServiceTests {

    @InjectMocks
    private ItineraryController mockController;

    @Mock
    private ItineraryService itineraryService;

    @Test
    public void testShareItinerary() {
        // Parametros input
        long id_itinerary = 1;
        long id_user = 5;
        long id_target = 3;

        // Creando Mock
        SharingRequestDto mockRequest = new SharingRequestDto();
        mockRequest.setId_user_origin(id_target);
        mockRequest.setId_user_target(id_user);
        mockRequest.setId_itinerary(id_itinerary);
        mockRequest.setText("Itinerary compartido exitosamente");

        // mock de servicio
        ResponseEntity<SharingResponseDto> response = mockController.share_itinerary(mockRequest);

        // assert response
        assert response.getStatusCode().equals(HttpStatus.OK);
    }
}
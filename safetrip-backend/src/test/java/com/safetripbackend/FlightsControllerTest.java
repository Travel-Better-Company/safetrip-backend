package com.safetripbackend;

import com.safetripbackend.controller.FlightsController;
import com.safetripbackend.service.FlightsService;
import com.safetripbackend.mappers.FlightsMapper;
import com.safetripbackend.dto.FlightsResponseDTO;
import com.safetripbackend.entity.Flights;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FlightsControllerTest {

    @InjectMocks
    private FlightsController flightsController;

    @Mock
    private FlightsService flightsService;

    @Mock
    private FlightsMapper flightsMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFlightById() {
        Long flightId = 1L;
        Flights flight = new Flights();
        flight.setId(flightId);
        FlightsResponseDTO responseDTO = new FlightsResponseDTO();
        responseDTO.setId(flightId);

        when(flightsService.getFlightById(flightId)).thenReturn(flight);
        when(flightsMapper.mapToResponseDTO(flight)).thenReturn(responseDTO);

        ResponseEntity<FlightsResponseDTO> responseEntity = flightsController.getFlightById(flightId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }


}
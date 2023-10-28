package com.safetripbackend;

import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Flights;
import com.safetripbackend.repository.FlightsRepository;
import com.safetripbackend.service.FlightsService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import java.util.Date;

public class FlightsServiceTest {

    @InjectMocks
    private FlightsService flightsService;

    @Mock
    private FlightsRepository flightsRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFlightsByDepartureCityId() {
        // Arrange
        Long departureCityId = 1L;
        List<Flights> expectedFlights = new ArrayList<>();
        Mockito.when(flightsRepository.findByDepartureCityId(departureCityId)).thenReturn(expectedFlights);

        // Act
        List<Flights> result = flightsService.getFlightsByDepartureCityId(departureCityId);

        // Assert
        assertEquals(expectedFlights, result);
    }

    @Test
    public void testGetFlightsByDepartureAndArrivalCityId() {
        // Arrange
        Long departureCityId = 1L;
        Long arrivalCityId = 2L;
        List<Flights> expectedFlights = new ArrayList<>();
        Mockito.when(flightsRepository.findByDepartureCityIdAndArrivalCityId(departureCityId, arrivalCityId)).thenReturn(expectedFlights);

        // Act
        List<Flights> result = flightsService.getFlightsByDepartureAndArrivalCityId(departureCityId, arrivalCityId);

        // Assert
        assertEquals(expectedFlights, result);
    }

    @Test
    public void testGetFlightById() {
        // Arrange
        Long flightId = 1L;
        Flights expectedFlight = new Flights(new Cities(), new Cities(), new Date(), new Date());
        Optional<Flights> optionalFlight = Optional.of(expectedFlight);
        Mockito.when(flightsRepository.findById(flightId)).thenReturn(optionalFlight);

        // Act
        Flights result = flightsService.getFlightById(flightId);

        // Assert
        assertEquals(expectedFlight, result);
    }
}
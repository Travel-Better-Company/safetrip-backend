package com.safetripbackend.service;

import com.safetripbackend.entity.Flights;
import com.safetripbackend.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {
    private final FlightsRepository flightsRepository;

    @Autowired
    public FlightsService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<Flights> getFlightsByDepartureCityId(Long departureCityId) {
        return flightsRepository.findByDepartureCityId(departureCityId);
    }

    public List<Flights> getFlightsByDepartureAndArrivalCityId(Long departureCityId, Long arrivalCityId) {
        return flightsRepository.findByDepartureCityIdAndArrivalCityId(departureCityId, arrivalCityId);
    }

    public Flights getFlightById(Long id) {
        Optional<Flights> optionalFlight = flightsRepository.findById(id);
        return optionalFlight.orElse(null);
    }
}
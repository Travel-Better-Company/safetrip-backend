package com.safetripbackend.controller;

import com.safetripbackend.service.FlightsService;
import com.safetripbackend.mappers.FlightsMapper;
import com.safetripbackend.dto.FlightsResponseDTO;
import com.safetripbackend.entity.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/flights")
public class FlightsController {

    private final FlightsService flightsService;
    private final FlightsMapper flightsMapper;

    @Autowired
    public FlightsController(FlightsService flightsService, FlightsMapper flightsMapper) {
        this.flightsService = flightsService;
        this.flightsMapper = flightsMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<FlightsResponseDTO> getFlightById(@PathVariable Long id) {
        Flights flight = flightsService.getFlightById(id);
        if (flight != null) {
            FlightsResponseDTO responseDTO = flightsMapper.mapToResponseDTO(flight);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byDepartureCity/{departureCityId}")
    public ResponseEntity<List<FlightsResponseDTO>> getFlightsByDepartureCity(@PathVariable Long departureCityId) {
        List<Flights> flights = flightsService.getFlightsByDepartureCityId(departureCityId);
        List<FlightsResponseDTO> responseDTOs = flights.stream()
                .map(flightsMapper::mapToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/byDepartureAndArrivalCity/{departureCityId}/{arrivalCityId}")
    public ResponseEntity<List<FlightsResponseDTO>> getFlightsByDepartureAndArrivalCity(@PathVariable Long departureCityId, @PathVariable Long arrivalCityId) {
        List<Flights> flights = flightsService.getFlightsByDepartureAndArrivalCityId(departureCityId, arrivalCityId);
        List<FlightsResponseDTO> responseDTOs = flights.stream()
                .map(flightsMapper::mapToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }
}
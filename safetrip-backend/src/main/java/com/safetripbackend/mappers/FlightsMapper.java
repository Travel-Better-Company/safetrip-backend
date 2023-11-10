package com.safetripbackend.mappers;

import com.safetripbackend.dto.FlightsRequestDTO;
import com.safetripbackend.dto.FlightsResponseDTO;
import com.safetripbackend.dto.CityResponseDto;
import com.safetripbackend.entity.Flights;
import com.safetripbackend.entity.Cities;
import org.springframework.stereotype.Component;

@Component
public class FlightsMapper {

    public FlightsResponseDTO mapToResponseDTO(Flights flight) {
        FlightsResponseDTO responseDTO = new FlightsResponseDTO();
        responseDTO.setId(flight.getId());
        responseDTO.setDepartureCity(mapToCityResponseDto(flight.getDepartureCity()));
        responseDTO.setArrivalCity(mapToCityResponseDto(flight.getArrivalCity()));
        responseDTO.setStartDate(flight.getStartDate());
        responseDTO.setEndDate(flight.getEndDate());
        return responseDTO;
    }

    private CityResponseDto mapToCityResponseDto(Cities city) {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setName(city.getName());
        cityResponseDto.setSights(city.getSights());
        return cityResponseDto;
    }

    public Flights mapToEntity(FlightsRequestDTO requestDTO) {
        Flights flight = new Flights();
        flight.setDepartureCity(new Cities(requestDTO.getDepartureCityId()));
        flight.setArrivalCity(new Cities(requestDTO.getArrivalCityId()));
        flight.setStartDate(requestDTO.getStartDate());
        flight.setEndDate(requestDTO.getEndDate());
        return flight;
    }
}
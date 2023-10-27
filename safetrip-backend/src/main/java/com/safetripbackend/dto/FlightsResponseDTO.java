package com.safetripbackend.dto;

import lombok.Data;
import com.safetripbackend.dto.CityResponseDto;

import java.util.Date;

@Data
public class FlightsResponseDTO {
    private Long id;
    private CityResponseDto departureCity;
    private CityResponseDto arrivalCity;
    private Date startDate;
    private Date endDate;
}
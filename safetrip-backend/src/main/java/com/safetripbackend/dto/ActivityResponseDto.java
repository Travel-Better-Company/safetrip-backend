package com.safetripbackend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ActivityResponseDto {
    private long id;
    private String name;
    private LocalDate iniDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ItineraryResponseDto itinerary; //Se cambio su posición y se le cambio a una clase response
}


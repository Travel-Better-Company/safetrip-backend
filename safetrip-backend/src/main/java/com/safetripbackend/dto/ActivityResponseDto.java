package com.safetripbackend.dto;

import com.safetripbackend.entity.Itineraries;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ActivityResponseDto {
    private int id;
    private Itineraries itineraries;
    private String name;
    private LocalDate iniDate;
    private LocalTime startTime;
    private LocalTime endTime;
}


package com.safetripbackend.PlannerItinerarie.dto;

import com.safetripbackend.PlannerItinerarie.entity.Itineraries;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityResponseDto {
    private long id;
    private Itineraries itineraries;
    private String name;
    private LocalDate iniDate;
    private LocalDate endDate;
}
}

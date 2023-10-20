package com.safetripbackend.PlannerItinerarie.dto;

import lombok.Data;

@Data
public class ItineraryResponseDto {
    private int id;
    private String name;
    private String location;
    private String sights;
}

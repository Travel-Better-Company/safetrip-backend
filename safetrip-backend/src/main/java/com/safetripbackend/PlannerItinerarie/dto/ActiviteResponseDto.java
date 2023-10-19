package com.safetripbackend.PlannerItinerarie.dto;

import lombok.Data;

@Data
public class ActiviteResponseDto {
    private int id;
    private int id_itinerario;
    private String name_activity;
}

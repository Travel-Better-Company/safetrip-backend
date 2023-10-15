package com.safetripbackend.PlannerItinerarie.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ItinerarieRequestDto {
    private int id;
    private String name;
    private LocalDate ini_date;
    private LocalDate end_date;
    private int id_user;
    private int id_city;
}

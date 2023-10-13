package com.safetripbackend.PlannerItinerarie.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Itineraries {
    // Atributos
    private int id;
    private String name;
    private LocalDate ini_date;
    private LocalDate end_date;
    private int id_user;
    private int id_city;
}

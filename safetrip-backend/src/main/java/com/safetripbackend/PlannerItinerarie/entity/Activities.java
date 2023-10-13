package com.safetripbackend.PlannerItinerarie.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Activities {
    private int id;
    private int id_itinerario;
    private String name_activity;
    private LocalDate ini_date;
    private LocalDate end_date;

}

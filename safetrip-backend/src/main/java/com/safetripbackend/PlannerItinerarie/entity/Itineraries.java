package com.safetripbackend.PlannerItinerarie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class Itineraries {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate ini_date;
    private LocalDate end_date;
    private int id_user;
    private int id_city;
}

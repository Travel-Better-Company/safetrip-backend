package com.safetripbackend.PlannerItinerarie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name; // Ejemplo de atributo de tipo cadena
    private String email; // Ejemplo de atributo de tipo cadena
    private String password; // Ejemplo de atributo de tipo cadena
}

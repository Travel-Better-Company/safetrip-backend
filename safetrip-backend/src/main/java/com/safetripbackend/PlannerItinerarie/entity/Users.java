package com.safetripbackend.PlannerItinerarie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name; // Ejemplo de atributo de tipo cadena
    private String email; // Ejemplo de atributo de tipo cadena
    private String password; // Ejemplo de atributo de tipo cadena
}

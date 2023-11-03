package com.safetripbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Itineraries {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate ini_date;
    private LocalDate end_date;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_city")
    private Cities city;

}

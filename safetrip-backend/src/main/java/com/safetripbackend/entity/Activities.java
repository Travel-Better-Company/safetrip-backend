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
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate  iniDate;
    private LocalDate  endDate;

    @ManyToOne
    @JoinColumn(name = "id_itinerario")
    private Itineraries itinerary;

}

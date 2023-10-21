package com.safetripbackend.PlannerItinerarie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ActivityRequestDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "El nombre de la actividad no debe exceder los 50 caracteres")
    private String name;
    @NotNull(message = "El id del itinerario no debe ser null")
    private int id_itinerary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate iniDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}

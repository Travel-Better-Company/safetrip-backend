package com.safetripbackend.PlannerItinerarie.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ActivitesRequestDto {
    @Size(max = 50, message = "El nombre de la actividad no debe exceder los 50 caracteres")
    private String name_activity;
    @NotNull(message = "El id del itinerario no debe ser null")
    private int id_itinerario;
}

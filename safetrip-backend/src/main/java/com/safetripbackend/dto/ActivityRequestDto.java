package com.safetripbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ActivityRequestDto {
    @Size(max = 50, message = "El nombre de la actividad no debe exceder los 50 caracteres")
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate iniDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime; // Hora de inicio

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime; // Hora de fin

    @NotNull(message = "El id del itinerario no debe ser null")
    private Long id_itinerary;
}

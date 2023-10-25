package com.safetripbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ActivityRequestDto {
    @Size(max = 50, message = "El nombre de la actividad no debe exceder los 50 caracteres")
    private String name;
    @NotNull(message = "El id del itinerario no debe ser null")
    private Long id_itinerary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate iniDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}

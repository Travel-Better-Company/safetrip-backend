package com.safetripbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ItineraryRequestDto {
    @Size(max = 50, message = "El nombre del itinerario no debe exceder los 50 caracteres")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ini_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    @NotNull(message = "El id del usuario no debe ser null")
    private Long id_user;
    @NotNull(message = "El id de la ciudad no debe ser null")
    private Long id_city;
}

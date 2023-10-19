package com.safetripbackend.PlannerItinerarie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CitieRequestDto {
    @NotBlank(message = "El nombre de la ciudad no debe estar en blanco")
    @Size(max = 50, message = "El nombre de la ciudad no debe exceder los 50 caracteres")
    private String name;
    @Size(max = 50, message = "La localidad no debe exceder los 50 caracteres")
    private String location;
    @Size(max = 50, message = "Las vistas no debe exceder los 50 caracteres")
    private String sights;
}

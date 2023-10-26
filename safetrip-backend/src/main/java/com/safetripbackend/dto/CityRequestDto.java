package com.safetripbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CityRequestDto {
    @NotBlank(message = "El nombre de la ciudad no debe estar en blanco")
    @Size(max = 50, message = "El nombre de la ciudad no debe exceder los 50 caracteres")
    private String name;
    @Size(max = 50, message = "Las vistas no deben exceder los 50 caracteres")
    private List<String> sights;
}

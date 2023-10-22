package com.safetripbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPreferencesRequestDto {
    @NotNull(message = "El id del usuario no debe ser null")
    private int userId;
    @NotNull(message = "El id de la ciudad no debe ser null")
    private int cityId;
}

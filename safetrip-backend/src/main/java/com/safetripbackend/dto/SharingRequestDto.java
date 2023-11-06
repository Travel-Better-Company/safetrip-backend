package com.safetripbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SharingRequestDto {

    @NotNull(message = "El id del usuario origen no debe ser null")
    private Long id_user_origin;
    @NotNull(message = "El id del usuario objetivo no debe ser null")
    private Long id_user_target;
    @NotNull(message = "El id del itinerario no debe ser null")
    private Long id_itinerary;

    @JsonIgnore
    private String text;
}

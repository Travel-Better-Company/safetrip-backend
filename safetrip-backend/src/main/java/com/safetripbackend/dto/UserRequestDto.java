package com.safetripbackend.PlannerItinerarie.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @Size(max = 50, message = "El nombre del usuario no debe exceder los 50 caracteres")
    private String name; // Ejemplo de atributo de tipo cadena
    @Size(max = 50, message = "El correo electronico no debe exceder los 50 caracteres")
    private String email; // Ejemplo de atributo de tipo cadena
    @Size(max = 50, message = "La contraseña no debe exceder los 50 caracteres")
    private String password; // Ejemplo de atributo de tipo cadena
}

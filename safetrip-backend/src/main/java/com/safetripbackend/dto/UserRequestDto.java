package com.safetripbackend.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotNull(message = "El name del usuario no debe ser null")
    @Size(max = 50, message = "El nombre del usuario no debe exceder los 50 caracteres")
    private String name;
    @NotNull(message = "El email del usuario no debe ser null")
    @Size(max = 50, message = "El correo electronico no debe exceder los 50 caracteres")
    private String email;
    @NotNull(message = "El password del usuario no debe ser null")
    @Size(max = 50, message = "La contraseña no debe exceder los 50 caracteres")
    private String password;

}

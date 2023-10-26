package com.safetripbackend.dto;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @Size(max = 50, message = "El nombre del usuario no debe exceder los 50 caracteres")
    private String name;
    @Size(max = 50, message = "El correo electronico no debe exceder los 50 caracteres")
    private String email;
    @Size(max = 50, message = "La contraseña no debe exceder los 50 caracteres")
    private String password;

}

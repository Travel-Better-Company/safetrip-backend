package com.safetripbackend.user.resource;

@Data
public class UsuarioResource {
    private Long id;
    private String nombre;
    private String email;
    private String numeroTarjeta; 
    private String fechaExpiracion; 
    private String cvv; 
 
}
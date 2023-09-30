package com.safetripbackend.user.domain.entity;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String cvv;
    

}
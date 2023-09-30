package com.safetripbackend.subscription.domain.entity;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    
    private String estadoSuscripcion; 
    private String idTransaccionIZiPay; 
    

    
}

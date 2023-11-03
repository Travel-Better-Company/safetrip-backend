package com.safetripbackend.dto;

import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ItineraryResponseDto {
    private Long id;
    private String name;
    private LocalDate ini_date;
    private LocalDate end_date;
    private UserResponseDto users;//antes era solo "user" por eso no mostraba en el postman
    private CityResponseDto city;
}

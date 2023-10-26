package com.safetripbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CityResponseDto {
    private int id;
    private String name;
    private List<String> sights;
}

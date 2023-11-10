package com.safetripbackend.dto;

import lombok.Data;
import com.safetripbackend.dto.CityResponseDto;

import java.util.List;

@Data
public class CityResponseDto {
    private Long id;
    private String name;
    private List<String> sights;
}

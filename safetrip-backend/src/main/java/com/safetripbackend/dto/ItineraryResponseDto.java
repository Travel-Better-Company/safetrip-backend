package com.safetripbackend.dto;

import com.safetripbackend.entity.Cities;
import lombok.Data;

@Data
public class ItineraryResponseDto {
    private int id;
    private String name;
    private String location;
    private String sights;
    private Cities city;
}

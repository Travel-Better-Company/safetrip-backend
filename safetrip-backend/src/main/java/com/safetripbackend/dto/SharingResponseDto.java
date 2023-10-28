package com.safetripbackend.dto;

import lombok.Data;

@Data
public class SharingResponseDto {
    private long id_origin;
    private long id_target;
    private long id_itinerary;
    private String text;
}


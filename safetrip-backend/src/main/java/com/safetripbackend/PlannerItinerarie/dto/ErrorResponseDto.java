package com.safetripbackend.PlannerItinerarie.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponseDto {
    private String message;
    private LocalDateTime timestamp;
    private List<String> validationErrors;

    public ErrorResponseDto(String message, LocalDateTime timestamp){
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorResponseDto(String message, LocalDateTime timestamp,List<String> validationErrors){
        this.message = message;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
    }
}

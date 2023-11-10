package com.safetripbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseDto(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

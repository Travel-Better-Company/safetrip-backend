package com.safetripbackend.dto;

import lombok.Data;

@Data
public class LogoutResponseDto {
    private String message;

    public LogoutResponseDto(String message) {
        this.message = message;
    }
}

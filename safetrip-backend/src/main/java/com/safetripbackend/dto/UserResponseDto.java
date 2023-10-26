package com.safetripbackend.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private String password;
}

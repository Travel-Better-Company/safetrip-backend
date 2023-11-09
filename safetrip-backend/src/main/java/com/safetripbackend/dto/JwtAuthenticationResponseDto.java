package com.safetripbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.spi.Tokens;

@Getter
@Builder
@Setter
public class JwtAuthenticationResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserResponseDto userResponseDto;
}

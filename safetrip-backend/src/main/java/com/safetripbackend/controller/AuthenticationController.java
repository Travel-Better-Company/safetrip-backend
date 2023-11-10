package com.safetripbackend.controller;

import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.dto.SinginRequestDto;
import com.safetripbackend.dto.SignupRequestDto;
import com.safetripbackend.dto.JwtAuthenticationDtoResponse;
import com.safetripbackend.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupRequestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationDtoResponse> signin(@RequestBody SinginRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
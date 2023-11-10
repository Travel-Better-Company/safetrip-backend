package com.safetripbackend.controller;

import com.safetripbackend.dto.SignupResquestDto;
import com.safetripbackend.dto.SinginRequestDto;
import com.safetripbackend.dto.JwtAuthenticationResponseDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupResquestDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signin(@RequestBody SinginRequestDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}

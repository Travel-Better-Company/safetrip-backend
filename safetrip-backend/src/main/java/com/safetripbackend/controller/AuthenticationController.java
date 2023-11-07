package com.safetripbackend.controller;

import com.safetripbackend.dto.JwtAuthenticationDtoResponse;
import com.safetripbackend.dto.SignUpDtoRequest;
import com.safetripbackend.dto.SigninDtoRequest;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignUpDtoRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationDtoResponse> signin(@RequestBody SigninDtoRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
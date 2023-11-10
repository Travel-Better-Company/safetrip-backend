package com.safetripbackend.security;

import com.safetripbackend.entity.Users;
import com.safetripbackend.entity.Role;
import com.safetripbackend.dto.JwtAuthenticationResponseDto;
import com.safetripbackend.dto.SinginRequestDto;
import com.safetripbackend.dto.SignupResquestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.safetripbackend.mappers.UserMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserResponseDto signup(SignupResquestDto request) {
        var user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return userMapper.mapToDTO(user);
    }

    public JwtAuthenticationResponseDto signin(SinginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Correo o usuario incorrectos."));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().tokenType(jwt).userResponseDto(userMapper.mapToDTO(user)).build();
    }
}
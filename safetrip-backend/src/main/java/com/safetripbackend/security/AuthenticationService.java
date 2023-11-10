package com.safetripbackend.security;

import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.entity.Users;
import com.safetripbackend.entity.Role;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.dto.SignupRequestDto;
import com.safetripbackend.dto.SinginRequestDto;
import com.safetripbackend.dto.JwtAuthenticationDtoResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.safetripbackend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserResponseDto signup(SignupRequestDto request) {
        var user = Users.builder().id(request.getId()).name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        return  userMapper.mapToDTO(user);
    }


    public JwtAuthenticationDtoResponse signin(SigninDtoRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Correo o usuario incorrectos."));


        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationDtoResponse.builder().token(jwt).userDto(userMapper.mapToDTO(user)).build();
    }
}

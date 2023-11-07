package com.safetripbackend.security;

import com.safetripbackend.dto.JwtAuthenticationDtoResponse;
import com.safetripbackend.dto.SignUpDtoRequest;
import com.safetripbackend.dto.SigninDtoRequest;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.entity.Role;
import com.safetripbackend.entity.Users;
import com.safetripbackend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //esto para hacer signup
    public UserResponseDto signup(SignUpDtoRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
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
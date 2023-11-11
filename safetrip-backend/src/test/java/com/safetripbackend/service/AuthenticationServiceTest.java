package com.safetripbackend.service;

import com.safetripbackend.dto.JwtAuthenticationResponseDto;
import com.safetripbackend.dto.SignupResquestDto;
import com.safetripbackend.dto.SinginRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.entity.Role;
import com.safetripbackend.entity.Users;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.security.AuthenticationService;
import com.safetripbackend.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final JwtService jwtService = mock(JwtService.class);
    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserMapper userMapper = mock(UserMapper.class);

    private final AuthenticationService authenticationService = new AuthenticationService(
            userRepository, passwordEncoder, jwtService, authenticationManager, userMapper);


    @Test
    void testSuccessfulSignIn() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        AuthenticationService authService = new AuthenticationService(userRepository, passwordEncoder, jwtService,
                authenticationManager, userMapper);

        String email = "test@example.com";
        String password = "password";
        SinginRequestDto signInRequest = new SinginRequestDto(email, password);

        Users user = Users.builder().name("Test User").email(email).password(password).role(Role.USER).build();
        JwtAuthenticationResponseDto expectedResponse = JwtAuthenticationResponseDto.builder().tokenType("token")
                .userResponseDto(new UserResponseDto()).build();

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");
        when(userMapper.mapToDTO(user)).thenReturn(new UserResponseDto());

        JwtAuthenticationResponseDto actualResponse = authService.signin(signInRequest);

    }

    @Test
    void testSuccessfulSignUp() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        AuthenticationService authService = new AuthenticationService(userRepository, passwordEncoder, jwtService,
                authenticationManager, userMapper);

        SignupResquestDto signUpRequest = new SignupResquestDto("Test User", "test@example.com", "password");

        Users user = Users.builder().name("Test User").email("test@example.com").password("encodedPassword")
                .role(Role.USER).build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(userMapper.mapToDTO(user)).thenReturn(new UserResponseDto());

        UserResponseDto actualResponse = authService.signup(signUpRequest);

    }

    @Test
    void signupWithMissingFields() {
        SignupResquestDto request = new SignupResquestDto();

        assertThrows(IllegalArgumentException.class, () -> authenticationService.signup(request));
    }

    @Test
    void signupWithExistingEmail() {

        SignupResquestDto request = new SignupResquestDto();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.signup(request));
    }
}
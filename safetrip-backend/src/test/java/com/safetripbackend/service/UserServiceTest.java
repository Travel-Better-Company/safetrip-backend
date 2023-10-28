package com.safetripbackend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.entity.Users;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Given
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");
        userRequest.setPassword("password");

        Users userEntity = new Users();
        userEntity.setId(1L);
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setName(userRequest.getName());
        userEntity.setPassword(userRequest.getPassword());

        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(1L);
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setName(userRequest.getName());

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userMapper.resourceToEntity(userRequest)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToResponseResource(userEntity)).thenReturn(userResponse);

        // When
        UserResponseDto result = userService.createUser(userRequest);

        // Then
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getName(), result.getName());
    }

    @Test
    public void testCreateUserWithExistingEmail() {
        // Given
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setEmail("existing@example.com");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        // Then
        assertThrows(ResourceAlreadyExistsException.class, () -> userService.createUser(userRequest));
    }

    @Test
    public void testUpdateUser() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setEmail("updated@example.com");
        userRequest.setName("Updated User");
        userRequest.setPassword("newpassword");

        Users existingUser = new Users();
        existingUser.setId(userId);
        existingUser.setEmail("existing@example.com");
        existingUser.setName("Existing User");
        existingUser.setPassword("oldpassword");

        Users updatedUser = new Users();
        updatedUser.setId(userId);
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setName(userRequest.getName());
        updatedUser.setPassword(userRequest.getPassword());

        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(userId);
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setName(userRequest.getName());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        when(userMapper.entityToResponseResource(updatedUser)).thenReturn(userResponse);

        // When
        UserResponseDto result = userService.updateUser(userId, userRequest);

        // Then
        assertNotNull(result);
        assertEquals(userResponse.getId(), result.getId());
        assertEquals(userResponse.getEmail(), result.getEmail());
        assertEquals(userResponse.getName(), result.getName());
    }

    @Test
    public void testUpdateUserWithNonExistingUser() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequest = new UserRequestDto();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userId, userRequest));
    }
}

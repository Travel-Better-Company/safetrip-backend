package com.safetripbackend.service;
import com.safetripbackend.dto.ItineraryResponseDto;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;
    @Mock
    private ItineraryService itineraryService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //US: Eliminar cuenta de usuario -> Escenario
    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        // Mock userRepository.existsById
        when(userRepository.existsById(userId)).thenReturn(true);

        // Mock itineraryService.getAllItinerary
        List<ItineraryResponseDto> itinerarios = new ArrayList<>();  // Simula itinerarios asociados al usuario
        ItineraryResponseDto itinerary1 = new ItineraryResponseDto();
        itinerary1.setId(1L);
        itinerary1.setUsers(new UserResponseDto());
        itinerary1.getUsers().setId(userId);
        itinerarios.add(itinerary1);

        when(itineraryService.getAllItinerary()).thenReturn(itinerarios);

        // Mock itineraryService.deleteItinerary
        doNothing().when(itineraryService).deleteItinerary(anyLong());

        // Mock userRepository.deleteById
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).existsById(userId);
        verify(itineraryService, times(1)).getAllItinerary();
        verify(itineraryService, times(1)).deleteItinerary(itinerary1.getId());
        verify(userRepository, times(1)).deleteById(userId);
    }

    //US: Registro de nueva cuenta (anterior) -> Escenario Exitoso: Creación de Nueva Cuenta
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

    //US: Seguir a Otros Viajeros  -> Escenario
    @Test
    public void testFollowUser() {
        // Given
        Long followerId = 1L;
        Long followedId = 2L;

        Users follower = new Users();
        follower.setId(followerId);
        follower.setFollowersIds(new ArrayList<>());
        follower.setFollowersCount(0L);

        Users followed = new Users();
        followed.setId(followedId);

        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));
        when(userRepository.save(follower)).thenReturn(follower);

        // When
        Users result = userService.followUser(followerId, followedId);

        // Then
        assertNotNull(result);
        assertEquals(follower.getId(), result.getId());
        assertTrue(result.getFollowersIds().contains(followedId));
        assertEquals(1, result.getFollowersCount());

        // Verificar que el método save fue invocado una vez
        verify(userRepository, times(1)).save(follower);
    }
    //US: Seguir a Otros Viajeros  -> Escenario

    @Test
    public void testUnfollowUser() {
        // Given
        Long followerId = 1L;
        Long followedId = 2L;

        Users follower = new Users();
        follower.setId(followerId);
        follower.setFollowersIds(new ArrayList<>(Collections.singletonList(followedId)));
        follower.setFollowersCount(1L);

        Users followed = new Users();
        followed.setId(followedId);

        UserResponseDto expectedResponse = new UserResponseDto();

        // Ajusta la configuración del mock para simular el comportamiento del repositorio y del mapper
        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));
        when(userRepository.save(any(Users.class))).thenReturn(follower);
        when(userMapper.entityToResponseResource(follower)).thenReturn(expectedResponse);

        // When
        UserResponseDto actualResponse = userService.unfollowUser(followerId, followedId);

        // Then
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        // Verificar que los métodos del repositorio se llamaron correctamente
        verify(userRepository, times(1)).findById(followerId);
        verify(userRepository, times(1)).findById(followedId);
        verify(userRepository, times(1)).save(follower);

        // Verificar que el método del mapper se llamó correctamente
        verify(userMapper, times(1)).entityToResponseResource(follower);
    }

    /// US:Registro de nueva cuenta (anterior) -> Escenario Alternativo: Correo Electrónico ya Registrado
    @Test
    public void testCreateUserWithExistingEmail() {
        // Given
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setEmail("existing@example.com");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        // Then
        assertThrows(ResourceAlreadyExistsException.class, () -> userService.createUser(userRequest));
    }

    ///US: Actualización de información de usuario -> Escenario Exitoso:
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
    /// US: Validación de la actualización de usuario  -> Escenario exitoso
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

package com.safetripbackend.service;

import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.entity.Users;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return userMapper.entityListToResponseResourceList(users);
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userResource) {
        if (userRepository.existsByEmail(userResource.getEmail())) {
            throw new ResourceAlreadyExistsException("El usuario con este email ya existe:" + userResource.getEmail());
        }

        Users user = userMapper.resourceToEntity(userResource);
        user = userRepository.save(user);

        return userMapper.entityToResponseResource(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto userResource) {
        Optional<Users> optionalCustomer = userRepository.findById(userId);

        if (optionalCustomer.isPresent()) {
            Users user = optionalCustomer.get();

            user.setName(userResource.getName());
            user.setPassword(userResource.getPassword());
            user.setEmail(userResource.getEmail());

            user = userRepository.save(user);
            return userMapper.entityToResponseResource(user);
        } else {
            throw new ResourceNotFoundException("Usuario con este id no exist: " + userId);
        }
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Usuario con este id no exist: " + userId);
        }

        userRepository.deleteById(userId);
    }
}

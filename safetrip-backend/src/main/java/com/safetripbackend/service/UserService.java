package com.safetripbackend.service;

import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.entity.Users;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.UserMapper;
import com.safetripbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final UserMapper userMapper;
    public final ItineraryService itineraryService;
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
        //Con esto se eliminarn los itinerarios asociados a este user
        List<ItineraryResponseDto> itinerarios =   itineraryService.getAllItinerary();
        List<Long> idItinerariesToDelete = new ArrayList<>();
        for(ItineraryResponseDto itinerary:  itinerarios){
            if (itinerary.getUsers().getId().equals(userId)){
                idItinerariesToDelete.add(itinerary.getId());
            }
        }
        for(Long id:idItinerariesToDelete ){
            itineraryService.deleteItinerary(id);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public Users followUser(Long followerId, Long followedId) {
        Users follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + followerId));

        Users followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + followedId));

        if (!follower.getFollowersIds().contains(followedId)) {
            follower.getFollowersIds().add(followedId);
            follower.setFollowersCount(follower.getFollowersCount() + 1);
            userRepository.save(follower);
        }

        return follower;
    }
    @Transactional
    public UserResponseDto unfollowUser(Long followerId, Long followedId) {
        Users follower = userRepository.findById(followerId).orElse(null);
        Users followed = userRepository.findById(followedId).orElse(null);

        if (follower == null || followed == null) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } else {
            if (follower.getFollowersIds().contains(followedId)) {
                follower.getFollowersIds().remove(followedId);
                follower.setFollowersCount(follower.getFollowersCount() - 1);
                follower = userRepository.save(follower);
                return userMapper.entityToResponseResource(follower);
            } else {
                throw new IllegalStateException("El usuario no sigue a la persona indicada");
            }
        }
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe"));
            }
        };
    }
}

package com.safetripbackend.controller;

import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.safetripbackend.entity.Users;
import java.util.List;
import com.safetripbackend.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUsers(@Valid @RequestBody UserRequestDto userResource){
        UserResponseDto responseResource =  userService.createUser(userResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllItineraries() {
        List<UserResponseDto> itineraryResponseResource = userService.getAllUsers();
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }
    @PostMapping("/{followerId}/follow/{followedId}")
    public Users followUser(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId) {
        Users follower = userRepository.findById(followerId).orElse(null);
        Users followed = userRepository.findById(followedId).orElse(null);

        if (follower == null || followed == null) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        } else {
            if (!follower.getFollowersIds().contains(followedId))

            {
                follower.getFollowersIds().add(followedId);
                follower.setFollowersCount(follower.getFollowersCount() + 1);
                userRepository.save(follower);
            }
        }
        return follower;
    }

}

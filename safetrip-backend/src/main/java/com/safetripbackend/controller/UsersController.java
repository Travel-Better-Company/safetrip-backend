package com.safetripbackend.controller;

import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.repository.UserRepository;
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


@RestController
@RequestMapping("/api/v1/users")
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

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserRequestDto userResource) {
        UserResponseDto userResponseResource = userService.updateUser(userId, userResource);
        return new ResponseEntity<>(userResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> itineraryResponseResource = userService.getAllUsers();
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }


    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Users> followUser(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId) {
        try {
            Users follower = userService.followUser(followerId, followedId);
            return new ResponseEntity<>(follower, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{followerId}/unfollow/{followedId}")
    public ResponseEntity<UserResponseDto> unfollowUser(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId) {
        try {
            UserResponseDto responseDto = userService.unfollowUser(followerId, followedId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/followers")
    public List<Users> getAllUsers1() {
        List<Users> allUsers = userRepository.findAll();
        return allUsers;
    }
}

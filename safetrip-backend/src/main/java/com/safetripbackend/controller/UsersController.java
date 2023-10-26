package com.safetripbackend.followers.controller;

import com.safetripbackend.followers.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.safetripbackend.followers.entity.Users;
import java.util.List;
import com.safetripbackend.followers.repository.UsersRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/{followerId}/follow/{followedId}")
    public Users followUser(@PathVariable("followerId") int followerId, @PathVariable("followedId") int followedId) {
        Users follower = usersRepository.findById(followerId).orElse(null);
        Users followed = usersRepository.findById(followedId).orElse(null);

        if (follower == null || followed == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        } else {
            if (!follower.getFollowersIds().contains(followedId)) {
                follower.getFollowersIds().add(followedId);
                follower.setFollowersCount(follower.getFollowersCount() + 1);
                usersRepository.save(follower);
            }
        }
        return follower;
    }
    @GetMapping("/all")
    public List<Users> getAllUsers() {
        List<Users> allUsers = usersRepository.findAll();
        return allUsers;
    }
}

package com.safetripbackend.controller;

import com.safetripbackend.controller.UsersController;
import com.safetripbackend.repository.UserRepository;
import com.safetripbackend.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UsersController userController;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFollowUser() {
        Long followerId = 1L;
        Long followedId = 2L;

        Users follower = new Users();
        follower.setId(followerId);
        follower.setFollowersCount(0L);
        follower.setFollowersIds(new ArrayList<>());

        Users followed = new Users();
        followed.setId(followedId);

        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));

        ResponseEntity<Users> responseEntity = userController.followUser(followerId, followedId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, follower.getFollowersCount());
        assertEquals(1, follower.getFollowersIds().size());
    }


}
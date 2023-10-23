package com.safetripbackend.controller;
import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.entity.Activities;
import com.safetripbackend.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivitiesController {
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> createActivity(@Valid @RequestBody ActivityRequestDto activityResource){
        ActivityResponseDto responseResource = activityService.createActivity(activityResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }
}
package com.safetripbackend.controller;
import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.service.ActivityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> createActivity(@Valid @RequestBody ActivityRequestDto activityResource){
        ActivityResponseDto responseResource = activityService.createActivity(activityResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityResponseDto> updateActivity(
            @PathVariable Long activityId,
            @Valid @RequestBody ActivityRequestDto activityResource) {
        ActivityResponseDto activityResponseResource = activityService.updateActivity(activityId, activityResource);
        return new ResponseEntity<>(activityResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{itineraryId}")
    public ResponseEntity<List<ActivityResponseDto>> getActivitiesByItineraryId(@PathVariable Long itineraryId) {
        List<ActivityResponseDto> activityResponseResource = activityService.getActivitiesByItineraryId(itineraryId);
        return new ResponseEntity<>(activityResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities(){
        List<ActivityResponseDto> activityResponseResource = activityService.getAllActivities();
        return new ResponseEntity<>(activityResponseResource, HttpStatus.OK);
    }
}
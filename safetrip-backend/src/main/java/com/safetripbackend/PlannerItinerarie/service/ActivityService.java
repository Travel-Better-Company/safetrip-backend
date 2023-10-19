package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.*;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ActivityService {
     @Autowired
    private ActivityRepository activityRepository;

    //

    public List<Activity> findActivitiesByName(String activityName) {
        return activityRepository.findActivitiesByName(activityName);
    }

    public List<Activity> findActivitiesByName(String activityName) {
        return activityRepository.findActivitiesByName(activityName);
    }

    public Optional<Activity> findActivityById(Long id) {
        return activityRepository.findById(id);
    }
}
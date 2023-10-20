package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.ActivityRequestDto;
import com.safetripbackend.PlannerItinerarie.dto.ActivityResponseDto;
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import com.safetripbackend.PlannerItinerarie.exception.ResourceAlreadyExistsException;
import com.safetripbackend.PlannerItinerarie.exception.ResourceNotFoundException;
import com.safetripbackend.PlannerItinerarie.mappers.ActivityMapper;
import com.safetripbackend.PlannerItinerarie.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public List<ActivityResponseDto> getAllActivities() {
        List<Activities> activities1 = activityRepository.findAll();
        return activityMapper.entityListToResponseResourceList(activities1);
    }
    public ActivityResponseDto getActivityById(Long activityId) {
        Activities activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
        return activityMapper.entityToResponseResource(activity);
    }

    @Transactional
    public ActivityResponseDto createActivity(ActivityRequestDto activityResource) {
        if (activityRepository.existsByNameAndIniDate(activityResource.getName_activity(), activityResource.getIniDate())) {
            throw new ResourceAlreadyExistsException("Actividad con el mismo nombre y fecha inicial ya existe");
        }

        Activities activity = activityMapper.resourceToEntity(activityResource);
        activity = activityRepository.save(activity);

        return activityMapper.entityToResponseResource(activity);
    }
    @Transactional
    public ActivityResponseDto updateActivity(Long activityId, ActivityRequestDto activityResource) {
        Optional<Activities> optionalActivity = activityRepository.findById(activityId);

        if (optionalActivity.isPresent()) {
            Activities activity = optionalActivity.get();

            activity.setFirstName(activityResource.getFirstName());
            activity.setLastName(activityResource.getLastName());
            activity.setEmail(activityResource.getEmail());

            customer = customerRepository.save(customer);
            return customerMapper.entityToResponseResource(customer);
        } else {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }
    }

    public List<Activities> findActivitiesByName(String activityName) {
        return activityRepository.findActivitiesByName(activityName);
    }
    public Optional<Activities> findActivityById(Long id) {
        return activityRepository.findById(id);
    }
}
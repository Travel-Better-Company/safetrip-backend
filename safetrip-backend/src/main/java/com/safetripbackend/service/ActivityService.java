package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.ActivityRequestDto;
import com.safetripbackend.PlannerItinerarie.dto.ActivityResponseDto;
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import com.safetripbackend.PlannerItinerarie.exception.ResourceAlreadyExistsException;
import com.safetripbackend.PlannerItinerarie.exception.ResourceNotFoundException;
import com.safetripbackend.PlannerItinerarie.mappers.ActivityMapper;
import com.safetripbackend.PlannerItinerarie.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
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
        if (activityRepository.existsByNameAndIniDate(activityResource.getName(), activityResource.getIniDate())) {
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

            activity.setName(activityResource.getName());
            activity.setIniDate(activityResource.getIniDate());
            activity.setEndDate(activityResource.getEndDate());

            activity = activityRepository.save(activity);
            return activityMapper.entityToResponseResource(activity);
        } else {
            throw new ResourceNotFoundException("Actividad con ese id no existe: " + activityId);
        }
    }
    @Transactional
    public void deleteActivity(Long activityId) {
        if (!activityRepository.existsById(activityId)) {
            throw new ResourceNotFoundException("Actividad con ese id no existe: " + activityId);
        }
        activityRepository.deleteById(activityId);
    }
    public List<Activities> findActivitiesByName(String activityName) {
        return activityRepository.findActivitiesByName(activityName);
    }
    public Optional<Activities> findActivityById(Long id) {
        return activityRepository.findById(id);
    }

}
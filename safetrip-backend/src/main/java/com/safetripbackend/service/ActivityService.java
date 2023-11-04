package com.safetripbackend.service;

import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.entity.Activities;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.ActivityMapper;
import com.safetripbackend.repository.ActivityRepository;

import com.safetripbackend.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final ItineraryRepository itineraryRepository;
    @Transactional
    public ActivityResponseDto createActivity(ActivityRequestDto activityResource) {
        // Validar el id_itinerary
        validateItineraryId(activityResource.getId_itinerary());

        if (activityRepository.existsByNameAndIniDate(activityResource.getName(), activityResource.getIniDate())) {
            throw new ResourceAlreadyExistsException("Actividad con el mismo nombre y fecha inicial ya existe");
        }

        Activities activity = activityMapper.resourceToEntity(activityResource);
        activity=activityRepository.save(activity);

        return activityMapper.entityToResponseResource(activity);
    }
    @Transactional
    public ActivityResponseDto updateActivity(Long activityId, ActivityRequestDto activityResource) {
        Optional<Activities> optionalActivity = activityRepository.findById(activityId);

        if (optionalActivity.isPresent()) {
            Activities activity = optionalActivity.get();

            // Validar el id_itinerary
            validateItineraryId(activityResource.getId_itinerary());

            activity.setName(activityResource.getName());
            activity.setIniDate(activityResource.getIniDate());
            activity.setStartTime(activityResource.getStartTime());
            activity.setEndTime(activityResource.getEndTime());

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
    public void validateItineraryId(Long itineraryId) {
        if (!itineraryRepository.existsById(itineraryId)) {
            throw new IllegalArgumentException("El id de itinerary no es válido: " + itineraryId);
        }
    }
    public List<ActivityResponseDto> getActivitiesByItineraryId(Long itineraryId) {
        List<Activities> activities = activityRepository.findAll();
        List<Activities> machtingActivities=new ArrayList<>();
        for (Activities activity : activities) {
            if(activity.getItinerary().getId().equals(itineraryId)){
                machtingActivities.add(activity);
            }
        }
        if(machtingActivities.isEmpty())
            throw new ResourceNotFoundException("No hay ningúna actividad con ese ID de itinerario ingresado");
        return activityMapper.entityListToResponseResourceList(machtingActivities);
    }

    public List<ActivityResponseDto> getAllActivities(){
        List<Activities> activities=activityRepository.findAll();
        return activityMapper.entityListToResponseResourceList(activities);
    }


}
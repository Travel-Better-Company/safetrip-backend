package com.safetripbackend.service;

import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.dto.ItineraryRequestDto;
import com.safetripbackend.entity.Activities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.exception.ValidationExpection;
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
        // Validar el id_itinerary y guardando este mismo
        long id_itinerary = activityResource.getId_itinerary();
        Itineraries itinerary = itineraryRepository.findById(id_itinerary)
                .orElseThrow(()->new ResourceNotFoundException("El itinerario con este Id no existe:" + id_itinerary));
        validateActivitesByItinerarieDateRange(activityResource, itinerary);
        //Verificando que si existe ya una actividad con ese nombre y fecha de inicio
        if (activityRepository.existsByNameAndIniDate(activityResource.getName(), activityResource.getIniDate())) {
            throw new ResourceAlreadyExistsException("Actividad con el mismo nombre y fecha inicial ya existe");
        }
        Activities activity = new Activities();
        activity.setName(activityResource.getName());
        activity.setStartTime(activityResource.getStartTime());
        activity.setIniDate(activityResource.getIniDate());
        activity.setEndTime(activityResource.getEndTime());
        activity.setItinerary(itinerary);

        activity=activityRepository.save(activity);

        return activityMapper.entityToResponseResource(activity);
    }
    @Transactional
    public ActivityResponseDto updateActivity(Long activityId, ActivityRequestDto activityResource) {
        Optional<Activities> optionalActivity = activityRepository.findById(activityId);

        if (optionalActivity.isPresent()) {
            Activities activity = optionalActivity.get();
            // Validar el id_itinerary

            long id_itinerary = activityResource.getId_itinerary();
            Itineraries itinerary = itineraryRepository.findById(id_itinerary)
                    .orElseThrow(()->new ResourceNotFoundException("El itinerario con este Id no existe:" + id_itinerary));
            validateActivitesByItinerarieDateRange(activityResource, itinerary);

            activity.setName(activityResource.getName());
            activity.setIniDate(activityResource.getIniDate());
            activity.setStartTime(activityResource.getStartTime());
            activity.setEndTime(activityResource.getEndTime());
            activity.setItinerary(itinerary);
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

    private void validateActivitesByItinerarieDateRange(ActivityRequestDto activityRequest, Itineraries itinerary) {
        if (activityRequest.getIniDate().isBefore(itinerary.getIni_date())
                && activityRequest.getIniDate().isAfter(itinerary.getEnd_date())) {
            throw new ValidationExpection("La fecha de asignada a la actividad: "+activityRequest.getIniDate()+" " +
                    "no está dentro de la fecha de ini_date: "+itinerary.getIni_date()+" y end_date: "+itinerary.getEnd_date());
        }
    }
}
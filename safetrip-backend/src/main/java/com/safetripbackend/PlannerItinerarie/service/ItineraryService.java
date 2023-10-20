package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.*;
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import com.safetripbackend.PlannerItinerarie.mappers.ActivityMapper;
import com.safetripbackend.PlannerItinerarie.repository.ActivityRepository;
import com.safetripbackend.PlannerItinerarie.repository.ItineraryRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItineraryService {


    /*Esto se piensa reemplazar con las entidades conectadas a la base de datos*/
    private final List<ActivityResponseDto> activities = new ArrayList<>();
    private final List<CityResponseDto> cities = new ArrayList<>();
    private final List<ItineraryResponseDto> itineraries = new ArrayList<>();
    private final List<UserResponseDto> users = new ArrayList<>();



    private final Validator validator;
    public ItineraryService(Validator validator){
        this.validator = validator;
        this.loadElements();
    }
    private void loadElements(){
        /*Falta cargar todos los elementos*/
        UserResponseDto user1 = new UserResponseDto();
        user1.setId(1);
        user1.setName("Alfredo");
        user1.setEmail("123@gmial.com");
        user1.setPassword("13245");
        ItineraryResponseDto itinerarie = new ItineraryResponseDto();
        itinerarie.setId(2);
        itinerarie.setName("Viaje de playas");

        ActivityResponseDto activitie1 = new ActivityResponseDto();
        activitie1.setId(3);
        activitie1.setName_activity("Juegos de playa en cartagena");
        activitie1.setId_itinerario(3);
    }

    /*Función para crear un itinerario*/
    public ItineraryResponseDto createItinerarie(ItineraryRequestDto itinerarieRequest){
        /*Pasa por todas las validaciones*/
        Set<ConstraintViolation<ItineraryRequestDto>> violations = validator.validate(itinerarieRequest);

        ItineraryResponseDto newItinerarie = new ItineraryResponseDto();
        return newItinerarie;
    }

    /*Validaciones que pasara nuestro itinerario, actividades y demás*/
    public void duplicateActivites(ItineraryResponseDto itinerarie) {
        List<ActivityResponseDto> itinerarieActivities = itinerarie.getAllActivities();

        Set<ActivityResponseDto> activitySet = new HashSet<>();
        List<ActivityResponseDto> duplicateActivities = new ArrayList<>();

        for (ActivityResponseDto activity : itinerarieActivities) {
            if (!activitySet.add(activity)) {
                // La actividad ya existe en el conjunto, por lo que es un duplicado.
                duplicateActivities.add(activity);
            }
        }

        if (!duplicateActivities.isEmpty()) {
            // Aquí puedes manejar los duplicados, por ejemplo, lanzar una excepción o realizar alguna otra acción.
            System.out.println("Actividades duplicadas encontradas: " + duplicateActivities);
        }
    }

    public void sizeActivities(){}
    public void dateError(){}

    public List<Itineraries> findByDestination(String destination) {
        List<Itineraries> itinerariesByDestination = new ArrayList<>();
        for (Itineraries itinerary : itineraries) {
            if (itinerary.getDestination().equalsIgnoreCase(destination)) {
                itinerariesByDestination.add(itinerary);
            }
        }
        return itinerariesByDestination;
    }



}

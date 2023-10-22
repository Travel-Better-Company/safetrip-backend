package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.*;
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import com.safetripbackend.PlannerItinerarie.entity.Itineraries;
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
    /*
    public ItineraryService(Validator validator){
        this.validator = validator;
        this.loadElements();
    }*/
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
        activitie1.setName("Juegos de playa en cartagena");
    }

    /*Función para crear un itinerario*/
    public ItineraryResponseDto createItinerarie(ItineraryRequestDto itinerarieRequest){
        /*Pasa por todas las validaciones*/
        Set<ConstraintViolation<ItineraryRequestDto>> violations = validator.validate(itinerarieRequest);

        ItineraryResponseDto newItinerarie = new ItineraryResponseDto();
        return newItinerarie;
    }
    /*
    public List<Itineraries> findByDestination(String destination) {
        List<Itineraries> itinerariesByDestination = new ArrayList<>();
        for (Itineraries itinerary : itineraries) {
            if (itinerary.getDestination().equalsIgnoreCase(destination)) {
                itinerariesByDestination.add(itinerary);
            }
        }
        return itinerariesByDestination;

    }*/



}

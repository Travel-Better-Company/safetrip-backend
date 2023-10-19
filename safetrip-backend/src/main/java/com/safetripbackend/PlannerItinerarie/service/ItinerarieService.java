package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ItinerarieService {
    /*Esto se piensa reemplazar con las entidades conectadas a la base de datos*/
    private final List<ActiviteResponseDto> activities = new ArrayList<>();
    private final List<CitieResponseDto> cities = new ArrayList<>();
    private final List<ItinerarieResponseDto> itineraries = new ArrayList<>();
    private final List<UserResponseDto> users = new ArrayList<>();

    private final Validator validator;
    public ItinerarieService(Validator validator){
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
        ItinerarieResponseDto itinerarie = new ItinerarieResponseDto();
        itinerarie.setId(2);
        itinerarie.setName("Viaje de playas");

        ActiviteResponseDto activitie1 = new ActiviteResponseDto();
        activitie1.setId(3);
        activitie1.setName_activity("Juegos de playa en cartagena");
        activitie1.setId_itinerario(3);
    }

    /*Función para crear un itinerario*/
    public ItinerarieResponseDto createItinerarie(ItinerarieRequestDto itinerarieRequest){
        /*Pasa por todas las validaciones*/
        Set<ConstraintViolation<ItinerarieRequestDto>> violations = validator.validate(itinerarieRequest);

        ItinerarieResponseDto newItinerarie = new ItinerarieResponseDto();
        return newItinerarie;
    }

    /*Validaciones que pasara nuestro itinerario, actividades y demás*/
    public void duplicateActivites(ItinerariesResponseDto itinerarie) {
        List<ActivitesResponseDto> itinerarieActivities = itinerarie.getActivities();

        Set<ActivitesResponseDto> activitySet = new HashSet<>();
        List<ActivitesResponseDto> duplicateActivities = new ArrayList<>();

        for (ActivitesResponseDto activity : itinerarieActivities) {
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

    public List<Itinerary> findByDestination(String destination) {
        List<Itinerary> itinerariesByDestination = new ArrayList<>();
        for (Itinerary itinerary : itineraries) {
            if (itinerary.getDestination().equalsIgnoreCase(destination)) {
                itinerariesByDestination.add(itinerary);
            }
        }
        return itinerariesByDestination;
    }



}

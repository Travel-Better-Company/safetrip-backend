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
public class ItinerarieService {
    /*Esto se piensa reemplazar con las entidades conectadas a la base de datos*/
    private final List<ActivitesResponseDto> activities = new ArrayList<>();
    private final List<CitiesResponseDto> cities = new ArrayList<>();
    private final List<ItinerariesResponseDto> itineraries = new ArrayList<>();
    private final List<UsersResponseDto> users = new ArrayList<>();

    private final Validator validator;
    public ItinerarieService(Validator validator){
        this.validator = validator;
        this.loadElements();
    }
    private void loadElements(){
        /*Falta cargar todos los elementos*/
        UsersResponseDto user1 = new UsersResponseDto();
        user1.setId(1);
        user1.setName("Alfredo");
        user1.setEmail("123@gmial.com");
        user1.setPassword("13245");
        ItinerariesResponseDto itinerarie = new ItinerariesResponseDto();
        itinerarie.setId(2);
        itinerarie.setName("Viaje de playas");

        ActivitesResponseDto activitie1 = new ActivitesResponseDto();
        activitie1.setId(3);
        activitie1.setName_activity("Juegos de playa en cartagena");
        activitie1.setId_itinerario(3);
    }

    /*Función para crear un itinerario*/
    public ItinerariesResponseDto createItinerarie(ItinerarieRequestDto itinerarieRequest){
        /*Pasa por todas las validaciones*/
        Set<ConstraintViolation<ItinerarieRequestDto>> violations = validator.validate(itinerarieRequest);

        ItinerariesResponseDto newItinerarie = new ItinerariesResponseDto();
        return newItinerarie;
    }

    /*Validaciones que pasara nuestro itinerario, actividades y demás*/
    public void ducplicateActivites(ItinerariesResponseDto itinerarie) {
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



}

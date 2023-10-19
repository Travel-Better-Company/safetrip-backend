package com.safetripbackend.PlannerItinerarie.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ItinerarieRepository {

    long countByUser(User user);

    List<Itinerary> findByDestination(String destination);

    //Buscar Itinerarios por Fecha de Creación:
    List<Itinerary> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);


}

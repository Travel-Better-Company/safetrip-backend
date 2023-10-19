package com.safetripbackend.PlannerItinerarie.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ItinerariesRepository {
    List<Itineraries> findByDestination(String destination);

    //Buscar Itinerarios por Fecha de Creación:
    List<Itineraries> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);


}

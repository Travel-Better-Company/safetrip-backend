package com.safetripbackend.repository;
import com.safetripbackend.entity.Itineraries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itineraries, Long> {
    List<Itineraries> findByDestination(String destination);
    //Buscar Itinerarios por Fecha de Creación:
    List<Itineraries> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    boolean existsByName(String name);

}

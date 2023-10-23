package com.safetripbackend.repository;
import com.safetripbackend.entity.Itineraries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itineraries, Long> {
    List<Itineraries> findByDestination(String destination);
    @Query("SELECT a FROM Itineraries a WHERE a.ini_date BETWEEN :startTime AND :endTime")
    List<Itineraries> findByCreationDateBetween(@Param("itinerary") Itineraries itinerary, @Param("startTime") String startTime, @Param("endTime") String endTime);

    boolean existsByName(String name);

}

package com.safetripbackend.PlannerItinerarie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activities, Long> {

    @Query("SELECT a FROM Activities a WHERE a.itinerary = :itinerary AND a.time BETWEEN :startTime AND :endTime")
    List<Activities> findActivitiesInTimeRange(@Param("itinerary") Itineraries itinerary, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("SELECT a FROM Activities a WHERE a.name = :activityName")
    List<Activities> findActivitiesByName(@Param("activityName") String activityName);

    Optional<Activities> findById(Long id);
}

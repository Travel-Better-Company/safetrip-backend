package com.safetripbackend.PlannerItinerarie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.itinerary = :itinerary AND a.time BETWEEN :startTime AND :endTime")
    List<Activity> findActivitiesInTimeRange(@Param("itinerary") Itinerary itinerary, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("SELECT a FROM Activity a WHERE a.name = :activityName")
    List<Activity> findActivitiesByName(@Param("activityName") String activityName);

    Optional<Activity> findById(Long id);
}

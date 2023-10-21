package com.safetripbackend.PlannerItinerarie.repository;

import com.safetripbackend.PlannerItinerarie.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activities, Long> {
    boolean existsByNameAndIniDate(String name, LocalDate iniDate);
    @Query("SELECT a FROM Activities a WHERE a.iniDate BETWEEN :startTime AND :endTime")
    List<Activities> findActivitiesInTimeRange(@Param("itinerary") Itineraries itinerary, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("SELECT a FROM Activities a WHERE a.name = :activityName")
    List<Activities> findActivitiesByName(@Param("activityName") String activityName);


}

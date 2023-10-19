package com.safetripbackend.PlannerItinerarie.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a WHERE a.itinerary = :itinerary AND a.time BETWEEN :startTime AND :endTime")
    List<Activity> findActivitiesInTimeRange(@Param("itinerary") Itinerary itinerary, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Optional<Activity> findById(Long id);

    // long countByItinerary(Itinerary itinerary)

    //void deleteById(Long id)
}
package com.safetripbackend.repository;

import com.safetripbackend.entity.*;
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
    List<Activities> findActivitiesInTimeRange(@Param("startTime") LocalDate startTime,@Param("endTime")  LocalDate endTime);

    List<Activities> findActivitiesByName(String name);


}
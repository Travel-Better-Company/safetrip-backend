package com.safetripbackend.repository;

import com.safetripbackend.entity.Itineraries;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itineraries, Long> {
    List<Itineraries> findItinerariesByCity_Location(String location);
    boolean existsByName(String name);

}

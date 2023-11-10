package com.safetripbackend.repository;

import com.safetripbackend.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findByDepartureCityId(Long departureCityId);
    List<Flights> findByDepartureCityIdAndArrivalCityId(Long departureCityId, Long arrivalCityId);
}
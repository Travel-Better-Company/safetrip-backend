package com.safetripbackend.repository;

import com.safetripbackend.dto.CityResponseDto;
import com.safetripbackend.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CityRepository extends JpaRepository<Cities,Long> {
    Optional<Cities> findByLocation(String location);

    boolean existsByLocation(String location);
}

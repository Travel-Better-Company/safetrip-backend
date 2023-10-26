package com.safetripbackend.service;

import com.safetripbackend.dto.*;
import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.ItineraryMapper;
import com.safetripbackend.repository.CityRepository;
import com.safetripbackend.repository.ItineraryRepository;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;
    private final CityRepository cityRepository;
    @Transactional
    public ItineraryResponseDto createItinerary(ItineraryRequestDto itineraryResource) {
        if(itineraryRepository.existsByName(itineraryResource.getName())){
            throw new ResourceAlreadyExistsException("Itineario con ese nombre ya existe");
        }
        Itineraries newItinerary = itineraryMapper.resourceToEntity(itineraryResource);
        newItinerary = itineraryRepository.save(newItinerary);
        return itineraryMapper.entityToResponseResource(newItinerary);
    }
    @Transactional
    public ItineraryResponseDto updateItinerary(Long itineraryId, ItineraryRequestDto itineraryResource) {
        Optional<Itineraries> optionalItinerary = itineraryRepository.findById(itineraryId);

        if (optionalItinerary.isPresent()) {
            Itineraries itinerary = optionalItinerary.get();

            itinerary.setName(itineraryResource.getName());
            itinerary.setIni_date(itineraryResource.getIni_date());
            itinerary.setEnd_date(itineraryResource.getEnd_date());
            Optional<Cities> optionalCity = cityRepository.findById((long)itineraryResource.getId_city());
            if(optionalCity.isPresent()){
                Cities cities = optionalCity.get();
                itinerary.setCity(cities);
            }
            else
            {
                throw new ResourceNotFoundException("Ciudad con ese id no existe "+ itineraryResource.getId_city());
            }

            itinerary = itineraryRepository.save(itinerary);

            return itineraryMapper.entityToResponseResource(itinerary);
        } else {
            throw new ResourceNotFoundException("Itinerario con ese id no existe"+itineraryId);
        }
    }
    @Transactional
    public  void deleteItinerary(Long itineraryId){
        if (!itineraryRepository.existsById(itineraryId)) {
            throw new ResourceNotFoundException("Itinerario con ese id no existe"+itineraryId);
        }

        itineraryRepository.deleteById(itineraryId);
    }
    public List<ItineraryResponseDto> getAllItinerary() {
        List<Itineraries> itinerary = itineraryRepository.findAll();
        return itineraryMapper.entityListToResponseResourceList(itinerary);
    }
    public List<ItineraryResponseDto> findByDestination(String destination) {
        List<Itineraries> itinerariesByDestination = itineraryRepository.findAll();
        List<Itineraries> matchingItineraries = new ArrayList<>();
        for (Itineraries itinerary : itinerariesByDestination) {
            if (itinerary.getCity().getName().equalsIgnoreCase(destination)) {
                matchingItineraries.add(itinerary);
            }
        }

        return itineraryMapper.entityListToResponseResourceList(matchingItineraries);
    }
}

package com.safetripbackend.service;

import com.safetripbackend.dto.*;
import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.entity.Users;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.ItineraryMapper;
import com.safetripbackend.repository.CityRepository;
import com.safetripbackend.repository.ItineraryRepository;

import com.safetripbackend.repository.UserRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
    private final UserRepository userRepository;
    @Transactional
    public ItineraryResponseDto createItinerary(long id_user,long id_city, ItineraryRequestDto itineraryResource) {
        Users user = userRepository.findById(id_user)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado con ID: "+id_user));
        Cities cities = cityRepository.findById(id_city)
                .orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada con ID: "+id_city));
        if(itineraryRepository.existsByName(itineraryResource.getName())){
            throw new ResourceAlreadyExistsException("Itineario con ese nombre ya existe");
        }
        Itineraries itinerary = new Itineraries();
        itinerary.setName(itineraryResource.getName());
        itinerary.setIni_date(itineraryResource.getIni_date());
        itinerary.setEnd_date(itineraryResource.getEnd_date());
        itinerary.setUsers(user);
        itinerary.setCity(cities);

        itineraryRepository.save(itinerary);
        return itineraryMapper.entityToResponseResource(itinerary);
    }
    @Transactional
    public ItineraryResponseDto updateItinerary(long itineraryId, long id_user, long id_city, ItineraryRequestDto itineraryResource) {
        Optional<Itineraries> optionalItinerary = itineraryRepository.findById(itineraryId);

        if (optionalItinerary.isPresent()) {
            Itineraries itinerary = optionalItinerary.get();

            Users user = userRepository.findById(id_user)
                    .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado con ID: "+id_user));
            Cities cities = cityRepository.findById(id_city)
                    .orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada con ID: "+id_city));
            itinerary.setName(itineraryResource.getName());
            itinerary.setIni_date(itineraryResource.getIni_date());
            itinerary.setEnd_date(itineraryResource.getEnd_date());
            itinerary.setUsers(user);
            itinerary.setCity(cities);

            itinerary = itineraryRepository.save(itinerary);

            return itineraryMapper.entityToResponseResource(itinerary);
        } else {
            throw new ResourceNotFoundException("Itinerario con ese id no existe"+itineraryId);
        }
    }
    @Transactional
    public  void deleteItinerary(long itineraryId){
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

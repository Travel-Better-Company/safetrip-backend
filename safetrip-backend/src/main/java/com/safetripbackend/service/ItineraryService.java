package com.safetripbackend.service;

import com.safetripbackend.dto.*;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.ItineraryMapper;
import com.safetripbackend.repository.ItineraryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;
    private final Validator validator;
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
            itinerary.setCity(itineraryResource.getCity());
            itinerary.setIni_date(itineraryResource.getIni_date());
            itinerary.setEnd_date(itineraryResource.getEnd_date());

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
        for (Itineraries itinerary : itinerariesByDestination) {
            if (itinerary.getCity().getLocation().equalsIgnoreCase(destination)) {
                itinerariesByDestination.add(itinerary);
            }
        }

        return itineraryMapper.entityListToResponseResourceList(itinerariesByDestination);
    }
}

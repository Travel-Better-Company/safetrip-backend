package com.safetripbackend.PlannerItinerarie.service;

import com.safetripbackend.PlannerItinerarie.dto.*;
<<<<<<< HEAD
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import com.safetripbackend.PlannerItinerarie.entity.Itineraries;
import com.safetripbackend.PlannerItinerarie.mappers.ActivityMapper;
import com.safetripbackend.PlannerItinerarie.repository.ActivityRepository;
=======
import com.safetripbackend.PlannerItinerarie.entity.Itineraries;
import com.safetripbackend.PlannerItinerarie.exception.ResourceAlreadyExistsException;
import com.safetripbackend.PlannerItinerarie.exception.ResourceNotFoundException;
import com.safetripbackend.PlannerItinerarie.mappers.ItineraryMapper;
>>>>>>> 7f298034ff1720814bfbe30697ac56ab22ff26bf
import com.safetripbackend.PlannerItinerarie.repository.ItineraryRepository;
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
<<<<<<< HEAD
    /*
    public ItineraryService(Validator validator){
        this.validator = validator;
        this.loadElements();
    }*/
    private void loadElements(){
        /*Falta cargar todos los elementos*/
        UserResponseDto user1 = new UserResponseDto();
        user1.setId(1);
        user1.setName("Alfredo");
        user1.setEmail("123@gmial.com");
        user1.setPassword("13245");
        ItineraryResponseDto itinerarie = new ItineraryResponseDto();
        itinerarie.setId(2);
        itinerarie.setName("Viaje de playas");

        ActivityResponseDto activitie1 = new ActivityResponseDto();
        activitie1.setId(3);
        activitie1.setName("Juegos de playa en cartagena");
=======
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
>>>>>>> 7f298034ff1720814bfbe30697ac56ab22ff26bf
    }
    @Transactional
    public  void deleteItinerary(Long itineraryId){
        if (!itineraryRepository.existsById(itineraryId)) {
            throw new ResourceNotFoundException("Itinerario con ese id no existe"+itineraryId);
        }

        itineraryRepository.deleteById(itineraryId);
    }
<<<<<<< HEAD
    /*
    public List<Itineraries> findByDestination(String destination) {
        List<Itineraries> itinerariesByDestination = new ArrayList<>();
        for (Itineraries itinerary : itineraries) {
            if (itinerary.getDestination().equalsIgnoreCase(destination)) {
                itinerariesByDestination.add(itinerary);
            }
        }
        return itinerariesByDestination;

    }*/



=======
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
>>>>>>> 7f298034ff1720814bfbe30697ac56ab22ff26bf
}

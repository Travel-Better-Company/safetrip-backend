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

    //En proceso
    @Transactional
    public void shareItinerary(long id_user_origin,long id_user_target, long id_itinerary) {
        Optional<Users> user_send = userRepository.findById(id_user_origin); //lo dejo en caso de que sea necesitado

        Optional<Users> user_target = Optional.ofNullable(userRepository.findById(id_user_target)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id_user_target)));

        Optional<Itineraries> optionalItinerary = itineraryRepository.findById(id_itinerary);

        Itineraries getting_itinerary = new Itineraries();
        Itineraries itinerary_operating = optionalItinerary.orElse(getting_itinerary); //verifica si es null

        //Clonando un itinerario para que lo tenga otro usuario
        ItineraryRequestDto itinerary_req = itineraryMapper.entityToResource(itinerary_operating);
        itinerary_req.setName(itinerary_req.getName()); //EN PROCESO: adquirir los gets
        itinerary_req.setIni_date(itinerary_req.getIni_date()); //aqui se deben de hacer gets para los itinerarios, ya que son privados
        itinerary_req.setEnd_date(itinerary_req.getEnd_date());
        itinerary_req.setCityId(itinerary_req.getCityId());
        itinerary_req.setUserId(id_user_target);

        createItinerary(id_user_target, itinerary_req.getCityId(), itinerary_req);
    }
}

package com.safetripbackend.service;

import com.safetripbackend.dto.*;
import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.entity.Users;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.exception.ValidationExpection;
import com.safetripbackend.mappers.ItineraryMapper;
import com.safetripbackend.repository.CityRepository;
import com.safetripbackend.repository.ItineraryRepository;

import com.safetripbackend.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ActivityService activityService;

    @Transactional
    public ItineraryResponseDto createItinerary(long id_user,long id_city, ItineraryRequestDto itineraryResource) {
        //Se valida las fechas del itinerario
        validateItinerariesByDateRange(itineraryResource);

        //Verificando la existencia de los usuarios y ciudades en la base de datos
        Users user = userRepository.findById(id_user)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado con ID: "+id_user));
        Cities cities = cityRepository.findById(id_city)
                .orElseThrow(()->new ResourceNotFoundException("Ciudad no encontrada con ID: "+id_city));
        //Se comprueba si el itinerario ya existe en la base de datos
        if(itineraryRepository.existsByNameAndUsers_Id(itineraryResource.getName(), itineraryResource.getUserId())){
            throw new ResourceAlreadyExistsException("Itinerario con ese nombre ya existe");
        }
        Itineraries itinerary = new Itineraries();
        itinerary.setName(itineraryResource.getName());
        itinerary.setIni_date(itineraryResource.getIni_date());
        itinerary.setEnd_date(itineraryResource.getEnd_date());
        itinerary.setUsers(user);
        itinerary.setCity(cities);

        //Se guarda en la base de datos el nuevo itinerario
        itineraryRepository.save(itinerary);
        return itineraryMapper.entityToResponseResource(itinerary);
    }
    @Transactional
    public ItineraryResponseDto updateItinerary(long itineraryId, long id_user, long id_city, ItineraryRequestDto itineraryResource) {
        //Se comprueba la existencia del itinerario
        Optional<Itineraries> optionalItinerary = itineraryRepository.findById(itineraryId);
        if (optionalItinerary.isPresent()) {
            Itineraries itinerary = optionalItinerary.get();
            validateItinerariesByDateRange(itineraryResource);
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
        //Con esto se eliminarn las actividades asociadas al itinerario, porque sino no se podría eliminar el itinerario ya que tienen una relacion con FK
        List<ActivityResponseDto> activities = activityService.getAllActivities();
        List<Long> idsActToDelete = new ArrayList<>();
        for( ActivityResponseDto activity: activities){
            if(activity.getItinerary().getId().equals(itineraryId)){
                idsActToDelete.add(activity.getId());
            }
        }
        for(Long  id:idsActToDelete ){
            activityService.deleteActivity(id);
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
        if(matchingItineraries.isEmpty())
            throw new ResourceNotFoundException("No hay ningún itinerario con esa ciudad ingrresada");
        return itineraryMapper.entityListToResponseResourceList(matchingItineraries);
    }

    @Transactional
    public void shareItinerary(long id_user_origin,long id_user_target, long id_itinerary) {
        Optional<Users> user_target = Optional.ofNullable(userRepository.findById(id_user_target)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id_user_target)));

        Optional<Itineraries> optionalItinerary = itineraryRepository.findById(id_itinerary);

        Itineraries getting_itinerary = new Itineraries();
        Itineraries itinerary_operating = optionalItinerary.orElse(getting_itinerary); //verifica si es null

        //Clonando un itinerario para que lo tenga otro usuario
        ItineraryRequestDto itinerary_req = itineraryMapper.entityToResource(itinerary_operating);
        itinerary_req.setName(itinerary_req.getName());
        itinerary_req.setIni_date(itinerary_req.getIni_date());
        itinerary_req.setEnd_date(itinerary_req.getEnd_date());
        itinerary_req.setCityId(itinerary_req.getCityId());
        itinerary_req.setUserId(id_user_target);

        createItinerary(id_user_target, itinerary_req.getCityId(), itinerary_req);
    }
    private void validateItinerariesByDateRange(ItineraryRequestDto itineraryRequest) {
        if (itineraryRequest.getEnd_date().isBefore(itineraryRequest.getIni_date()))
            throw new ValidationExpection("La fecha de fin no debe ser menor a la fecha de inicio");
    }

}

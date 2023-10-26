package com.safetripbackend.service;

import com.safetripbackend.dto.CityRequestDto;
import com.safetripbackend.dto.CityResponseDto;

import com.safetripbackend.entity.Cities;

import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ResourceNotFoundException;
import com.safetripbackend.mappers.CityMapper;
import com.safetripbackend.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityMapper cityMapper;
    private final CityRepository cityRepository;
    public List<CityResponseDto> getAllCities(){
        List<Cities> cities = cityRepository.findAll();
        return cityMapper.entityListToResponseResourceList(cities);
    }

    @Transactional
    public CityResponseDto createCity(CityRequestDto cityResource) {
        if(cityRepository.existsByName(cityResource.getName())){
            throw new ResourceAlreadyExistsException("Ciudad con ese nombre ya existe");
        }
        Cities newCity = cityMapper.resourceToEntity(cityResource);
        newCity = cityRepository.save(newCity);
        return cityMapper.entityToResponseResource(newCity);
    }

    @Transactional
    public CityResponseDto updateCity(Long cityId, CityRequestDto cityResource) {
        Optional<Cities> optionalCity = cityRepository.findById(cityId);

        if (optionalCity.isPresent()) {
            Cities city = optionalCity.get();
            city.setName(cityResource.getName());
            city.setSights(cityResource.getSights());

            city = cityRepository.save(city);

            return cityMapper.entityToResponseResource(city);
        } else {
            throw new ResourceNotFoundException("La ciudad con este Id: " + cityId + "no existe");
        }
    }

    @Transactional
    public void deleteCity(Long cityId){
        if (!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("La ciudad con este Id: "+cityId+ "no existe");
        }
        cityRepository.deleteById(cityId);
    }

}

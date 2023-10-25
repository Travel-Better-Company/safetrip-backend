package com.safetripbackend.mappers;

import com.safetripbackend.dto.CityRequestDto;
import com.safetripbackend.dto.CityResponseDto;
import com.safetripbackend.entity.Cities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    private final ModelMapper modelMapper;
    public CityMapper(ModelMapper modelMapper){ this.modelMapper = modelMapper;}
    public Cities resourceToEntity(CityRequestDto cityRequest){
        return modelMapper.map(cityRequest, Cities.class);
    }
    public CityRequestDto entityToResource(Cities City){
        return modelMapper.map(City, CityRequestDto.class);
    }
    public CityResponseDto entityToResponseResource(Cities City){
        return modelMapper.map(City, CityResponseDto.class);
    }

}

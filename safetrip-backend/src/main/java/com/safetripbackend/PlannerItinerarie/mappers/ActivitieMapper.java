package com.safetripbackend.PlannerItinerarie.mappers;

import com.safetripbackend.PlannerItinerarie.dto.ActiviteRequestDto;
import com.safetripbackend.PlannerItinerarie.dto.ActiviteResponseDto;
import com.safetripbackend.PlannerItinerarie.entity.Activities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivitieMapper {
    private final ModelMapper modelMapper;
    public ActivitieMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public Activities resourceToEntity(ActiviteRequestDto activiteRequest){
        return modelMapper.map(activiteRequest, Activities.class);
    }
    public ActiviteRequestDto entityToResource(Activities activitie){
        return modelMapper.map(activitie, ActiviteRequestDto.class);
    }
    public ActiviteResponseDto entityToResponseResource(Activities activitie){
        return modelMapper.map(activitie, ActiviteResponseDto.class);
    }

    public List<Activities> resourceListToEntityList (List<ActiviteRequestDto> activiteRequests){
        return activiteRequests
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }
    public List<ActiviteRequestDto> entityListToResourceList (List<Activities> activities){
        return activities
                .stream()
                .map(this::entityToResource)
                .toList();
    }
    public List<ActiviteResponseDto> entityListToResponseResourceList (List<Activities> activities){
        return activities
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}

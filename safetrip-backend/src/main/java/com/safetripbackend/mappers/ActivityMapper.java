package com.safetripbackend.mappers;

import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.entity.Activities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityMapper {
    private final ModelMapper modelMapper;
    public ActivityMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public Activities resourceToEntity(ActivityRequestDto activiteRequest){
        return modelMapper.map(activiteRequest, Activities.class);
    }
    public ActivityRequestDto entityToResource(Activities activitie){
        return modelMapper.map(activitie, ActivityRequestDto.class);
    }
    public ActivityResponseDto entityToResponseResource(Activities activitie){
        return modelMapper.map(activitie, ActivityResponseDto.class);
    }

    public List<Activities> resourceListToEntityList (List<ActivityRequestDto> activiteRequests){
        return activiteRequests
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }
    public List<ActivityRequestDto> entityListToResourceList (List<Activities> activities){
        return activities
                .stream()
                .map(this::entityToResource)
                .toList();
    }
    public List<ActivityResponseDto> entityListToResponseResourceList (List<Activities> activities){
        return activities
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}

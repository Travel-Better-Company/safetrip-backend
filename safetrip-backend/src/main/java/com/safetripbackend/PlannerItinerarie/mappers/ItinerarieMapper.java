package com.safetripbackend.PlannerItinerarie.mappers;
import com.safetripbackend.PlannerItinerarie.dto.ItinerarieRequestDto;
import com.safetripbackend.PlannerItinerarie.dto.ItinerarieResponseDto;
import com.safetripbackend.PlannerItinerarie.entity.Itineraries;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ItinerarieMapper {
    private final ModelMapper modelMapper;
    public ItinerarieMapper(ModelMapper modelMapper){this.modelMapper = modelMapper;}

    public Itineraries resourceToEntity(ItinerarieRequestDto itinerarieRequestDto){
        return modelMapper.map(itinerarieRequestDto, Itineraries.class);
    }
    public ItinerarieRequestDto entityToResource(Itineraries itinerarie){
        return modelMapper.map(itinerarie,ItinerarieRequestDto.class);
    }
    public ItinerarieResponseDto entityToResponseResource(Itineraries itinerarie){
        return modelMapper.map(itinerarie, ItinerarieResponseDto.class);
    }
    public List<Itineraries> resourceListToEntityList(List<ItinerarieRequestDto> itinerarieResources){
        return itinerarieResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }
    public List<ItinerarieRequestDto> entityListToResourceList(List<Itineraries> itineraries){
        return itineraries
                .stream()
                .map(this::entityToResource)
                .toList();
    }
    public List<ItinerarieResponseDto> entityListToResponseResourceList(List<Itineraries> itineraries){
        return itineraries
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}

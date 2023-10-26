package com.safetripbackend.mappers;
import com.safetripbackend.dto.ItineraryRequestDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.entity.Itineraries;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ItineraryMapper {
    private final ModelMapper modelMapper;
    public ItineraryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public Itineraries resourceToEntity(ItineraryRequestDto itinerarieRequestDto){
        return modelMapper.map(itinerarieRequestDto, Itineraries.class);
    }
    public ItineraryRequestDto entityToResource(Itineraries itinerarie){
        return modelMapper.map(itinerarie, ItineraryRequestDto.class);
    }
    public ItineraryResponseDto entityToResponseResource(Itineraries itinerarie){
        return modelMapper.map(itinerarie, ItineraryResponseDto.class);
    }
    public List<Itineraries> resourceListToEntityList(List<ItineraryRequestDto> itinerarieResources){
        return itinerarieResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }
    public List<ItineraryRequestDto> entityListToResourceList(List<Itineraries> itineraries){
        return itineraries
                .stream()
                .map(this::entityToResource)
                .toList();
    }
    public List<ItineraryResponseDto> entityListToResponseResourceList(List<Itineraries> itineraries){
        return itineraries
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

}

package com.safetripbackend.service;

import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.entity.Activities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.mappers.ActivityMapper;
import com.safetripbackend.repository.ActivityRepository;
import com.safetripbackend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import static  org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ActivityServiceTest {
    @InjectMocks
    ActivityService activityService;
    @Mock
    ActivityMapper activityMapper;
    @Mock
    ActivityRepository activityRepository;
    @Mock
    ItineraryRepository itineraryRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //US: Creación de Actividades
    @Test
    void testcreateActivity() {//1. Escenario Exitoso:
        //Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Itineraries itinerary = new Itineraries();
        ItineraryResponseDto itineraryResponseDto = new ItineraryResponseDto();
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();
        ActivityRequestDto activityRequestDto = new ActivityRequestDto();
        Activities activity = new Activities();
        String iniDate = "10-11-2023";
        String endDate = "11-11-2023";
        //
        itinerary.setId(20L);
        itinerary.setIni_date(LocalDate.parse(iniDate, formatter));
        itinerary.setEnd_date(LocalDate.parse(endDate, formatter));
        //
        activity.setName("Actividad A");
        activity.setIniDate(LocalDate.parse(iniDate, formatter));
        activity.setStartTime(LocalTime.parse("05:00:00"));
        activity.setEndTime(LocalTime.parse("07:00:00"));
        //
        activityRequestDto.setName(activity.getName());
        activityRequestDto.setIniDate(activity.getIniDate());
        activityRequestDto.setStartTime(activity.getStartTime());
        activityRequestDto.setEndTime(activity.getEndTime());
        activityRequestDto.setId_itinerary(itinerary.getId());
        //
        itineraryResponseDto.setId(itinerary.getId());
        //
        activityResponseDto.setName(activity.getName());
        activityResponseDto.setIniDate(activity.getIniDate());
        activityResponseDto.setStartTime(activity.getStartTime());
        activityResponseDto.setEndTime(activity.getEndTime());
        activityResponseDto.setItinerary(itineraryResponseDto);


        when(itineraryRepository.findById(itinerary.getId())).thenReturn(Optional.of(itinerary));
        when(activityRepository.existsByNameAndIniDate(activity.getName(), activity.getIniDate())).thenReturn(false);
        when(activityMapper.entityToResponseResource(activity)).thenReturn(activityResponseDto);

        // Act
        ActivityResponseDto result = activityService.createActivity(activityRequestDto);

        //Assert
        assertEquals(activityResponseDto, result);
    }
}

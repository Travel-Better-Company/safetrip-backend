package com.safetripbackend.service;

import com.safetripbackend.dto.ActivityRequestDto;
import com.safetripbackend.dto.ActivityResponseDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.entity.Activities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.exception.ResourceAlreadyExistsException;
import com.safetripbackend.exception.ValidationExpection;
import com.safetripbackend.mappers.ActivityMapper;
import com.safetripbackend.repository.ActivityRepository;
import com.safetripbackend.repository.ItineraryRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import static  org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ActivityServiceTest  {
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
    //US: Creación de Actividades -> Escemario
    @Test
    void testCreateActivity() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String iniDate = "10-11-2023";
        String endDate = "11-11-2023";

        // Create itinerary
        Itineraries itinerary = new Itineraries();
        itinerary.setId(20L);
        itinerary.setIni_date(LocalDate.parse(iniDate, formatter));
        itinerary.setEnd_date(LocalDate.parse(endDate, formatter));

        // Create activityRequestDto
        ActivityRequestDto activityRequestDto = new ActivityRequestDto();
        activityRequestDto.setName("Actividad A");
        activityRequestDto.setIniDate(LocalDate.parse(iniDate, formatter));
        activityRequestDto.setStartTime(LocalTime.parse("05:00:00"));
        activityRequestDto.setEndTime(LocalTime.parse("07:00:00"));
        activityRequestDto.setId_itinerary(itinerary.getId());

        // Create expected activityResponseDto
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();
        activityResponseDto.setName("Actividad A");
        activityResponseDto.setIniDate(LocalDate.parse(iniDate, formatter));
        activityResponseDto.setStartTime(LocalTime.parse("05:00:00"));
        activityResponseDto.setEndTime(LocalTime.parse("07:00:00"));
        activityResponseDto.setItinerary(new ItineraryResponseDto());
        activityResponseDto.getItinerary().setId(itinerary.getId());

        // Mock repository calls
        when(itineraryRepository.findById(itinerary.getId())).thenReturn(Optional.of(itinerary));
        when(activityRepository.existsByNameAndIniDate(activityRequestDto.getName(), activityRequestDto.getIniDate())).thenReturn(false);
        when(activityMapper.entityToResponseResource(any())).thenReturn(activityResponseDto);

        // Act
        ActivityResponseDto result = activityService.createActivity(activityRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(activityResponseDto.getName(), result.getName());
        assertEquals(activityResponseDto.getIniDate(), result.getIniDate());
        assertEquals(activityResponseDto.getStartTime(), result.getStartTime());
        assertEquals(activityResponseDto.getEndTime(), result.getEndTime());
        assertEquals(activityResponseDto.getItinerary().getId(), result.getItinerary().getId());
    }
    //US: Creación de Actividades -> Escemario
    @Test
    void testAddActivitiesToItinerary_ConflictingSchedules() {
        // Arrange
        // ... (configura el itinerario y las actividades según el escenario)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String iniDate = "10-11-2023";
        String endDate = "11-11-2023";

        // Create itinerary
        Itineraries itinerary = new Itineraries();
        itinerary.setId(21L);
        itinerary.setIni_date(LocalDate.parse(iniDate, formatter));
        itinerary.setEnd_date(LocalDate.parse(endDate, formatter));

        // Create activityRequestDto
        ActivityRequestDto activityRequestDto = new ActivityRequestDto();
        activityRequestDto.setName("Actividad A");
        activityRequestDto.setIniDate(LocalDate.parse(iniDate, formatter));
        activityRequestDto.setStartTime(LocalTime.parse("10:00:00"));// stardtime mayor que endtime
        activityRequestDto.setEndTime(LocalTime.parse("07:00:00"));
        activityRequestDto.setId_itinerary(itinerary.getId());

        // Create expected activityResponseDto
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();
        activityResponseDto.setName("Actividad A");
        activityResponseDto.setIniDate(LocalDate.parse(iniDate, formatter));
        activityResponseDto.setStartTime(LocalTime.parse("10:00:00"));
        activityResponseDto.setEndTime(LocalTime.parse("07:00:00"));
        activityResponseDto.setItinerary(new ItineraryResponseDto());
        activityResponseDto.getItinerary().setId(itinerary.getId());

        when(itineraryRepository.findById(itinerary.getId())).thenReturn(Optional.of(itinerary));

        // Act & Assert
        assertThrows(ValidationExpection.class, () -> activityService.createActivity(activityRequestDto));

    }
    //US: Creación de Actividades -> Escemario
    @Test
    void testCreateActivity_InvalidDateRange() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String iniDate = "10-11-2023";
        String endDate = "11-11-2023";

        // Create itinerary
        Itineraries itinerary = new Itineraries();
        itinerary.setId(20L);
        itinerary.setIni_date(LocalDate.parse(iniDate, formatter));
        itinerary.setEnd_date(LocalDate.parse(endDate, formatter));

        // Create activityRequestDto with invalid date
        ActivityRequestDto activityRequestDto = new ActivityRequestDto();
        activityRequestDto.setName("Actividad A");
        activityRequestDto.setIniDate(LocalDate.parse("09-11-2023", formatter)); // Invalid date
        activityRequestDto.setStartTime(LocalTime.parse("10:00:00"));
        activityRequestDto.setEndTime(LocalTime.parse("11:00:00"));
        activityRequestDto.setId_itinerary(itinerary.getId());

        when(itineraryRepository.findById(itinerary.getId())).thenReturn(Optional.of(itinerary));
        // Act & Assert
        try {
            ValidationExpection exception = assertThrows(ValidationExpection.class,
                    () -> activityService.createActivity(activityRequestDto));

            // Assert the exception message or any other details you want to check
            assertTrue(exception.getMessage().contains("La fecha de asignada a la actividad"));
        } catch (AssertionFailedError e) {
            // Agrega impresiones de depuración aquí para entender el estado actual de tus objetos
            ValidationExpection exception = assertThrows(ValidationExpection.class,
                    () -> activityService.createActivity(activityRequestDto));
            System.out.println("activityRequestDto: " + activityRequestDto);
            System.out.println("itinerary: " + itinerary);
            System.out.println(exception.getMessage());
            throw e; // Re-lanza la excepción después de imprimir información de depuración
        }



    }

}

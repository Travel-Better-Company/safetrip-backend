package com.safetripbackend.service;

import com.safetripbackend.dto.ItineraryRequestDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.entity.Cities;
import com.safetripbackend.entity.Itineraries;
import com.safetripbackend.entity.Users;
import com.safetripbackend.mappers.ItineraryMapper;
import com.safetripbackend.repository.CityRepository;
import com.safetripbackend.repository.ItineraryRepository;
import com.safetripbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static  org.junit.jupiter.api.Assertions.*;
public class ItineraryServiceTest {
    @InjectMocks
    private ItineraryService itineraryService;
    @Mock
    private ItineraryRepository itineraryRepository;
    @Mock
    private ItineraryMapper itineraryMapper;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateItinerary(){
        //Given (Arrange = preparación)
        long id_user = 1;
        long id_city = 1;
        String ini_date = "2023-11-01";
        String end_date = "2023-11-10";
        String name = "Itinerario de testeo";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate idate = LocalDate.parse(ini_date, formatter);
        LocalDate edate = LocalDate.parse(end_date, formatter);

        ItineraryRequestDto itineraryResource = new ItineraryRequestDto();
        itineraryResource.setName(name);
        itineraryResource.setIni_date(LocalDate.parse(ini_date,formatter));
        itineraryResource.setEnd_date(LocalDate.parse(end_date,formatter));
        itineraryResource.setUserId(id_user);
        itineraryResource.setCityId(id_city);

        Users user = new Users();
        user.setId(id_user);

        Cities city = new Cities();
        city.setId(id_city);

        Itineraries itinerary = new Itineraries();
        itinerary.setName(name);
        itinerary.setIni_date(idate);
        itinerary.setEnd_date(edate);
        itinerary.setUsers(user);
        itinerary.setCity(city);

        ItineraryResponseDto responseDto = new ItineraryResponseDto();

        when(userRepository.findById(id_user)).thenReturn(Optional.of(user));
        when(cityRepository.findById(id_city)).thenReturn(Optional.of(city));
        when(itineraryRepository.existsByName(itineraryResource.getName())).thenReturn(false);
        when(itineraryMapper.entityToResponseResource(itinerary)).thenReturn(responseDto);

        //When (Act - actuar -método a probar)
        ItineraryResponseDto result = itineraryService.createItinerary(id_user,id_city,itineraryResource);

        //Then (Assert - afirmación)
        assertNotNull(result);
        assertEquals(responseDto, result);
    }
    @Test
    public void testUpdateItinerary(){

    }
    @Test
    public void testDeleteItinerary(){

    }
    @Test
    public void testGetAllItineraries(){

    }
    @Test
    public void testFindByDestination(){

    }
}

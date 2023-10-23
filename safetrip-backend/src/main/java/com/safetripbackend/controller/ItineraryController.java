package com.safetripbackend.controller;
import com.safetripbackend.dto.ItineraryRequestDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.service.ItineraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itinerary")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<ItineraryResponseDto> createItinerary(@Valid @RequestBody ItineraryRequestDto itineraryResource) {
        ItineraryResponseDto responseResource = itineraryService.createItinerary(itineraryResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItineraryResponseDto>> getAllItineraries() {
        List<ItineraryResponseDto> itineraryResponseResource = itineraryService.getAllItinerary();
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }

    @PutMapping("/{itineraryId}")
    public ResponseEntity<ItineraryResponseDto> updateItinerary(
            @PathVariable Long itineraryId,
            @Valid @RequestBody ItineraryRequestDto itineraryResource) {
        ItineraryResponseDto itineraryResponseResource = itineraryService.updateItinerary(itineraryId, itineraryResource);
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{itineraryId}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable Long itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

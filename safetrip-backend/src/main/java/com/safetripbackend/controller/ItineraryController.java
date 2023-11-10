package com.safetripbackend.controller;
import com.safetripbackend.dto.ItineraryRequestDto;
import com.safetripbackend.dto.ItineraryResponseDto;
import com.safetripbackend.dto.SharingRequestDto;
import com.safetripbackend.dto.SharingResponseDto;
import com.safetripbackend.service.ItineraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itineraries")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<ItineraryResponseDto> createItinerary(@Valid @RequestBody ItineraryRequestDto itineraryResource) {
        ItineraryResponseDto responseResource = itineraryService.createItinerary(
                itineraryResource.getUserId(),
                itineraryResource.getCityId(),
                itineraryResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItineraryResponseDto>> getAllItineraries() {
        List<ItineraryResponseDto> itineraryResponseResource = itineraryService.getAllItinerary();
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }

    @PutMapping("/update/{itineraryId}")
    public ResponseEntity<ItineraryResponseDto> updateItinerary(
            @PathVariable Long itineraryId,
            @Valid @RequestBody ItineraryRequestDto itineraryResource) {
        ItineraryResponseDto itineraryResponseResource = itineraryService.updateItinerary(
                itineraryId,
                itineraryResource.getUserId(),
                itineraryResource.getCityId(),
                itineraryResource);
        return new ResponseEntity<>(itineraryResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{itineraryId}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable Long itineraryId) {
        itineraryService.deleteItinerary(itineraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{location}")
    public ResponseEntity <List<ItineraryResponseDto>> findItinerariesByCity_Location( @PathVariable String location) {
        List<ItineraryResponseDto> itineraryResponseResource = itineraryService.findByDestination(location);
        return new ResponseEntity<>(itineraryResponseResource,HttpStatus.OK);
    }


    @PostMapping("/share")
    public ResponseEntity<SharingResponseDto> share_itinerary
            (@Valid @RequestBody SharingRequestDto shareResource) {

        itineraryService.shareItinerary
                (shareResource.getId_user_origin(),
                shareResource.getId_user_target(),
                shareResource.getId_itinerary());

        SharingResponseDto shareResponseResource = new SharingResponseDto();
        shareResponseResource.setId_user_origin(shareResource.getId_user_origin());
        shareResponseResource.setId_user_target(shareResource.getId_user_target());
        shareResponseResource.setId_itinerary(shareResource.getId_itinerary());
        shareResponseResource.setText("Itinerary compartido exitosamente");
        return new ResponseEntity<>(shareResponseResource, HttpStatus.OK);
    }
}

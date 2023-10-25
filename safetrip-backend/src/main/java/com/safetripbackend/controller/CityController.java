package com.safetripbackend.controller;

import com.safetripbackend.dto.CityRequestDto;
import com.safetripbackend.dto.CityResponseDto;
import com.safetripbackend.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @PostMapping
    public ResponseEntity<CityResponseDto> createCity(@Valid @RequestBody CityRequestDto cityResource){
        CityResponseDto responseResource = cityService.createCity(cityResource);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityResponseDto>> getAllCities() {
        List<CityResponseDto> responseResource = cityService.getAllCities();
        return new ResponseEntity<>(responseResource, HttpStatus.OK);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityResponseDto> updateCity(
            @PathVariable Long cityId,
            @Valid @RequestBody CityRequestDto cityResource) {
        CityResponseDto responseResource = cityService.updateCity(cityId, cityResource);
        return new ResponseEntity<>(responseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

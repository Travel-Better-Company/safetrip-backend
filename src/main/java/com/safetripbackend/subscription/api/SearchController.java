package com.safetripbackend.subscription.api;

import com.safetripbackend.subscription.domain.entity.search;
import com.safetripbackend.subscription.domain.service.ServiceSearch;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api/searches")
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;
    public ResponseEntity<String> callExternalApi() {
        String apiUrl = "https://api.aviationstack.com/v1/flights?access_key=0049d3fa9948e6f0239ffa0cd15c1283";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }

    @Autowired
    private ServiceSearch searchService;

    @PostMapping
    public ResponseEntity<search> createSearch(@RequestBody search search) {
        search createdSearch = searchService.createSearch(search);
        return ResponseEntity.ok(createdSearch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<search> getSearch(@PathVariable int id) {
        search search = searchService.getSearchById(id);
        if (search != null) {
            return ResponseEntity.ok(search);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<search>> getAllSearches() {
        List<search> searches = searchService.getAllSearches();
        return ResponseEntity.ok(searches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<search> updateSearch(@PathVariable int id, @RequestBody search updatedSearch) {
        search search = searchService.getSearchById(id);
        if (search != null) {
            updatedSearch.setId(id);
            search updated = searchService.updateSearch(updatedSearch);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSearch(@PathVariable int id) {
        search search = searchService.getSearchById(id);
        if (search != null) {
            searchService.deleteSearch(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
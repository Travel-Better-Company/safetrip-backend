package com.safetripbackend.subscription.domain.service;

import com.safetripbackend.subscription.domain.entity.search;
import com.safetripbackend.subscription.domain.persitence.search_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSearch {

    @Autowired
    private search_repository searchRepository;

    public search createSearch(search search) {
        return searchRepository.save(search);
    }

    public search getSearchById(int id) {
        return searchRepository.findById(id);
    }

    public List<search> getAllSearches() {
        return searchRepository.findAll();
    }

    public void deleteSearch(int id) {
        searchRepository.delete(id);
    }

    public search updateSearch(search updatedSearch) {
        return searchRepository.update(updatedSearch);
    }
}

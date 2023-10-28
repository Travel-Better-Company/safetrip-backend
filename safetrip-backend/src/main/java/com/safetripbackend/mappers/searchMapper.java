package com.safetripbackend.subscription.mappers;

import com.safetripbackend.subscription.domain.dto.searchDto;
import com.safetripbackend.subscription.domain.entity.search;

public class searchMapper {
    public searchDto mapToDto(search search) {
        searchDto dto = new searchDto();
        dto.setId(search.getId());
        dto.setUserName(search.getName());
        dto.setIniDate(search.getIniDate());
        dto.setEndDate(search.getEndDate());
        dto.setCity(search.getCity());
        return dto;
    }

    public search mapToEntity(searchDto dto) {
        search search = new search();
        search.setId(dto.getId());
        search.setUserName(dto.getName());
        search.setIniDate(dto.getIniDate());
        search.setEndDate(dto.getEndDate());
        return search;
    }
}

package com.safetripbackend.subscription.mappers;

import com.safetripbackend.subscription.domain.entity.subscription;
import com.safetripbackend.subscription.domain.dto.subscriptionDto;
import jakarta.inject.Named;

@Named
public class subscriptionMapper {

    public subscriptionDto mapToDto(subscription subscription) {
        subscriptionDto dto = new subscriptionDto();
        dto.setId(subscription.getId());
        dto.setUserId(subscription.getUserId());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        return dto;
    }

    public subscription mapToEntity(subscriptionDto dto) {
        subscription subscription = new subscription();
        subscription.setId(dto.getId());
        subscription.setUserId(dto.getUserId());
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        return subscription;
    }
}

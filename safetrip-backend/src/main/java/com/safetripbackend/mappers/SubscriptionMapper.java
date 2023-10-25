package com.safetripbackend.mappers;
import com.safetripbackend.dto.SubscriptionDto;
import com.safetripbackend.entity.Subscription;

import jakarta.inject.Named;

@Named
public class SubscriptionMapper {

    public SubscriptionDto mapToDto(Subscription subscription) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId(subscription.getId());
        dto.setUserId(subscription.getUserId());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        return dto;
    }

    public Subscription mapToEntity(SubscriptionDto dto) {
        Subscription subscription = new Subscription();
        subscription.setId(dto.getId());
        subscription.setUserId(dto.getUserId());
        subscription.setStartDate(dto.getStartDate());
        subscription.setEndDate(dto.getEndDate());
        return subscription;
    }
}

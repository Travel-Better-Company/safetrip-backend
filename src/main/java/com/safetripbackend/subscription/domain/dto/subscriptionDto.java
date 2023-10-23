package com.safetripbackend.subscription.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class subscriptionDto {
    private Long id;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
package com.safetripbackend.dto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubscriptionRequestDTO {
    private Long userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}

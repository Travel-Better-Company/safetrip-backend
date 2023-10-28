package com.safetripbackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightsRequestDTO {
    private Long departureCityId;
    private Long arrivalCityId;
    private Date startDate;
    private Date endDate;
}
package com.safetripbackend.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Data
public class FlightsRequestDTO {
    private Long departureCityId;
    private Long arrivalCityId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
}
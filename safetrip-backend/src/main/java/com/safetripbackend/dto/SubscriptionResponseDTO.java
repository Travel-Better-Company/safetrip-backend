package com.safetripbackend.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.safetripbackend.entity.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionResponseDTO {
    private Long id;
    private Users user_register;
    private LocalDate startDate;
    private LocalDate endDate;
}

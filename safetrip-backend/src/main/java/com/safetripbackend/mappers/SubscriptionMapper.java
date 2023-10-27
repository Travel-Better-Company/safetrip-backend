package com.safetripbackend.mappers;
import com.safetripbackend.entity.Subscription;
import com.safetripbackend.dto.SubscriptionRequestDTO;
import com.safetripbackend.dto.SubscriptionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "user.id", target = "userId")
    SubscriptionResponseDTO subscriptionToSubscriptionResponseDTO(Subscription subscription);

    Subscription subscriptionRequestDTOToSubscription(SubscriptionRequestDTO requestDTO);

    @Mapping(source = "userId", target = "user.id")
    Subscription subscriptionResponseDTOToSubscription(SubscriptionResponseDTO responseDTO);
}
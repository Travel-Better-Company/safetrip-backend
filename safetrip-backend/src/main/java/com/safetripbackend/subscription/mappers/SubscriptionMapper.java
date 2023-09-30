package com.pe.safetripbackend.subscription.mappers;

import ccom.pe.safetripbackend.subscription.domain.entity.subscription;
import ccom.pe.safetripbackend.subscription.resource.SubscriptionResource;
import com.pe.safetripbackend.subscription.resource.SubscriptionResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscriptionMapper {
    private final ModelMapper modelMapper;

    public SubscriptionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Subscription resourceToEntity(SubscriptionResource subscriptionResource) {
        return modelMapper.map(subscriptionResource, Subscription.class);
    }

    public SubscriptionResource entityToResource(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionResource.class);
    }

    public SubscriptionResponseResource entityToResponseResource(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionResponseResource.class);
    }

    public List<Subscription> resourceListToEntityList(List<SubscriptionResource> subscriptionResources) {
        return subscriptionResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<SubscriptionResource> entityListToResourceList(List<Subscription> subscriptions) {
        return subscriptions
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<SubscriptionResponseResource> entityListToResponseResourceList(List<Subscription> subscriptions) {
        return subscriptions
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}

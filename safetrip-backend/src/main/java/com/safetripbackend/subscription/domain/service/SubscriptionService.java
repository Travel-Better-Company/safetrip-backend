package com.safetripbackend.subscription.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription crearSuscripcion(Subscription subscription) {
        
        return subscriptionRepository.save(subscription);
    }

    public Subscription obtenerSuscripcionPorId(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Suscripción no encontrada con ID: " + id));
    }

    public Subscription obtenerSuscripcionPorIdTransaccionIZiPay(String idTransaccionIZiPay) {
        return subscriptionRepository.findByIdTransaccionIZiPay(idTransaccionIZiPay)
                .orElseThrow(() -> new NotFoundException("Suscripción no encontrada con ID de transacción en iZiPay: " + idTransaccionIZiPay));
    }


}

package com.safetripbackend.subscription.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.safetripbackend.domain.entity.Subscription;
import com.safetripbackend.domain.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> crearSuscripcion(@RequestBody SubscriptionResource subscriptionResource) {
        Subscription subscription = subscriptionMapper.resourceToEntity(subscriptionResource);
        Subscription nuevaSuscripcion = subscriptionService.crearSuscripcion(subscription);
        SubscriptionResource nuevaSuscripcionResource = subscriptionMapper.entityToResource(nuevaSuscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSuscripcionResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> obtenerSuscripcionPorId(@PathVariable Long id) {
        Subscription subscription = subscriptionService.obtenerSuscripcionPorId(id);
        SubscriptionResource subscriptionResource = subscriptionMapper.entityToResource(subscription);
        return ResponseEntity.ok(subscriptionResource);
    }

    
}

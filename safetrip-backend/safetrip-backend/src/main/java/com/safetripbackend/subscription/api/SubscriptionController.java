package com.safetripbackend.subscription.api;

import com.safetripbackend.subscription.domain.entity.subscription;
import com.safetripbackend.subscription.domain.service.subscription.ServiceSubscription;
import com.safetripbackend.subscription.execption.SubscriptionAlreadyExistsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private ServiceSubscription subscriptionService;

    @PostMapping
    public ResponseEntity<subscription> createSubscription(@RequestBody subscription subscription) throws SubscriptionAlreadyExistsException {
        subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<subscription> getSubscription(@PathVariable Long id) {
        subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            return ResponseEntity.ok(subscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<subscription>> getAllSubscriptions() {
        List<subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<subscription> updateSubscription(@PathVariable Long id, @RequestBody subscription updatedSubscription) {
        subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            updatedSubscription.setId(id);
            subscription updated = subscriptionService.updateSubscription(updatedSubscription);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
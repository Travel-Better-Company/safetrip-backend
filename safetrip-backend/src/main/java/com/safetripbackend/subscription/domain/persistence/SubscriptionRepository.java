package com.safetripbackend.subscription.domain.persistence;

import com.safetripbackend.user.domain.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByIdTransaccionIZiPay(String idTransaccionIZiPay);
    
}

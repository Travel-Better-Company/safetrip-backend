package com.safetripbackend.subscription.domain.persitence;

import com.safetripbackend.subscription.domain.entity.subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class subscription_repository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public subscription save(subscription subscription) {
        entityManager.persist(subscription);
        return subscription;
    }

    public subscription findById(Long id) {
        return entityManager.find(subscription.class, id);
    }

    public List<subscription> findAll() {
        return entityManager.createQuery("SELECT s FROM subscription s", subscription.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        subscription subscription = findById(id);
        if (subscription != null) {
            entityManager.remove(subscription);
        }
    }
}
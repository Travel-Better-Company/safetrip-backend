package com.safetripbackend.repository;

import com.safetripbackend.entity.Subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubscriptionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Subscription save(Subscription subscription) {
        entityManager.persist(subscription);
        return subscription;
    }

    public Subscription findById(Long id) {
        return entityManager.find(Subscription.class, id);
    }

    public List<Subscription> findAll() {
        return entityManager.createQuery("SELECT s FROM Subscription s", Subscription.class).getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Subscription subscription = findById(id);
        if (subscription != null) {
            entityManager.remove(subscription);
        }
    }

    public Subscription findByUserId(String userId) {
        TypedQuery<Subscription> query = entityManager.createQuery(
                "SELECT s FROM Subscription s WHERE s.userId = :userId", Subscription.class);
        query.setParameter("userId", userId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
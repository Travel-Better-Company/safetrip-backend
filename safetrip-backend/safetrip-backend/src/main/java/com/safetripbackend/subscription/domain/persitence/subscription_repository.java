package com.safetripbackend.subscription.domain.persitence;

import com.safetripbackend.subscription.domain.entity.subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
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

    public subscription findByUserId(String userId) {
        TypedQuery<subscription> query = entityManager.createQuery(
                "SELECT s FROM subscription s WHERE s.userId = :userId", subscription.class);
        query.setParameter("userId", userId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
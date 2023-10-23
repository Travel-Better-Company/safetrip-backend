package com.safetripbackend.subscription.domain.persitence;

import com.safetripbackend.subscription.domain.entity.search;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class search_repository {

    @PersistenceContext
    private EntityManager entityManager;

    public search findById(int id) {
        return entityManager.find(search.class, id);
    }

    public List<search> findAll() {
        return entityManager.createQuery("SELECT s FROM search s", search.class).getResultList();
    }

    @Transactional
    public search save(search search) {
        entityManager.persist(search);
        return search;
    }

    @Transactional
    public search update(search search) {
        return entityManager.merge(search);
    }

    @Transactional
    public void delete(int id) {
        search search = findById(id);
        if (search != null) {
            entityManager.remove(search);
        }
    }
}

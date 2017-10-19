package com.fererlab.common.repository;


import com.fererlab.common.model.BaseModel;
import com.fererlab.common.model.Model;
import com.fererlab.common.property.Property;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class CommandRepository<T extends Model<PK>, PK extends Serializable> implements CRepository<T, PK> {

    @Inject
    @Property("persistence.unit")
    private String persistenceUnit;

    private static EntityManagerFactory entityManagerFactory;

    private Class<T> entityClass;

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        }
        return entityManagerFactory;
    }

    @SuppressWarnings("unchecked")
    public CommandRepository() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void create(T t) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T t) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T t) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        if (!entityManager.contains(t)) {
            t = entityManager.merge(t);
        }
        if (t instanceof BaseModel) {
            ((BaseModel) t).setDeleted(Boolean.TRUE);
            entityManager.merge(t);
        } else {
            entityManager.remove(t);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(PK id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        T t = entityManager.getReference(entityClass, id);
        if (t instanceof BaseModel) {
            ((BaseModel) t).setDeleted(Boolean.TRUE);
            entityManager.merge(t);
        } else {
            entityManager.remove(t);
        }
        entityManager.flush();
    }

}

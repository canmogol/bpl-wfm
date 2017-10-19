package com.fererlab.wssd.common.repository;


import com.fererlab.wssd.common.model.BaseModel;
import com.fererlab.wssd.common.model.Model;
import com.fererlab.wssd.property.Property;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CRUDRepository<T extends Model<PK>, PK extends Serializable> implements CommandRepository<T, PK>, QueryRepository<T, PK> {

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
    public CRUDRepository() {
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

    @Override
    public T findById(PK id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        T t = entityManager.find(entityClass, id);
        if (t != null && t instanceof BaseModel && ((BaseModel) t).isDeleted()) {
            // this entity is marked as deleted, return null
            return null;
        }
        return t;
    }

    @Override
    public List<T> findAll() {
        // What kind of a method is this "findAll" method? shouldn't it has some sort of a limit?
        // Hibernate implementation also thinks in the same way:
        // org.hibernate.ejb.AbstractQueryImpl   getMaxResults(){
        //      return maxResults == -1
        //          ? Integer.MAX_VALUE // stupid spec... MAX_VALUE??
        //          : maxResults;
        // }
        return findAll(0, Integer.MAX_VALUE);
    }

    @Override
    public List<T> findAll(Integer index, Integer numberOfRecords) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        if (BaseModel.class.isAssignableFrom(entityClass)) {
            criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
        }
        criteriaQuery.select(root);
        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(index).setMaxResults(numberOfRecords)
            .getResultList();
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long count() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        if (BaseModel.class.isAssignableFrom(entityClass)) {
            criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE));
        }
        criteriaQuery.select(criteriaBuilder.count(root));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        Long count = query.getSingleResult();
        return count;
    }

}

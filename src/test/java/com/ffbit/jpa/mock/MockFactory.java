package com.ffbit.jpa.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Mock factory for a domain {@link javax.persistence.Entity @Entity} type.
 * 
 * Inspired by:
 * <ul>
 * <li><a href="https://github.com/thoughtbot/factory_girl">factory_girl</a>
 * ruby gem</li>
 * <li>David Green's -
 * <a href="http://java.dzone.com/articles/patterns-better-unit-testing">article
 * </a>
 * </li>
 * </ul>
 * 
 * @author Dmytro Chyzhykov
 * 
 * @param <DomainType>
 *            a domain {@link javax.persistence.Entity @Entity} type
 */
@MockRepository
public abstract class MockFactory<DomainType> {

    @PersistenceContext
    private EntityManager em;

    public List<DomainType> build(int count) {
        List<DomainType> result = new ArrayList<DomainType>(count);

        for (int i = 0; i < count; i++) {
            result.add(build());
        }

        return result;
    }

    /**
     * Create a DomainType instance that's not persisted
     * 
     * @return a DomainType instance that's not persisted
     */
    public abstract DomainType build();

    public List<DomainType> create(int count) {
        List<DomainType> instances = build(count);

        for (DomainType instance : instances) {
            persist(instance);
        }

        return instances;
    }

    protected DomainType persist(DomainType entity) {
        em.persist(entity);

        return entity;
    }

    /**
     * Create a persisted DomainType instance
     * 
     * @return a persisted DomainType instance
     */
    public DomainType create() {
        return persist(build());
    }

}

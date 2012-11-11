package com.ffbit.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public abstract class AbstractJpaTest {
    @PersistenceContext
    protected EntityManager em;

    /**
     * Persist a domain {@link javax.persistence.Entity @Entity} type instance.<br />
     * Flush and clear a persistence context after that.
     * 
     * @param entity
     *            a domain {@link javax.persistence.Entity @Entity} type
     *            instance
     */
    public final void persistFlushAndClean(Object entity) {
        em.persist(entity);
        em.flush();
        em.clear();
    }

}

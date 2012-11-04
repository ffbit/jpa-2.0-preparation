package com.ffbit.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public abstract class AbstractJpaTest {
    @PersistenceContext
    protected EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    protected ApplicationContext context;

    // TODO: This method MUST be removed because of it's ugliness.
    /**
     * This method is very ugly because it doesn't participate in any Spring
     * transactions.
     * 
     * @param entity
     */
    protected void uglyPersist(Object entity) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

}

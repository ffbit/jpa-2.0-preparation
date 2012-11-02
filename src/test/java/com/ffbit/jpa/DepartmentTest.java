package com.ffbit.jpa;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest extends AbstractJpaTest {
    private String name;

    @Before
    public void setUp() throws Exception {
        name = "Sales Department";
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        Department department = new Department(name);

        em.persist(department);

        Department persisted = em.find(Department.class, department.getId());

        assertNotNull(persisted);
    }

}

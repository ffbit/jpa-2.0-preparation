package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ffbit.jpa.mock.DepartmentMockFactory;

public class DepartmentTest extends AbstractJpaTest {
    @Autowired
    DepartmentMockFactory departmentFactory;

    private Department department;
    private Department persistedDepartment;

    @Before
    public void setUp() throws Exception {
        department = departmentFactory.build();
        persistedDepartment = departmentFactory.create();
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        em.persist(department);

        Department persisted = em.find(Department.class, department.getId());

        assertThat(persisted, is(notNullValue()));
    }

    @Test
    public void itShouldAllowUpdateTheName() throws Exception {
        String newName = "Rebranded Department";
        persistedDepartment.setName(newName);

        em.merge(persistedDepartment);

        assertThat(persistedDepartment.getName(), is(equalTo(newName)));
    }

}

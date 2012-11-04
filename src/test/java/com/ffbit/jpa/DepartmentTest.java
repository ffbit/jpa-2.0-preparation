package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DepartmentTest extends AbstractJpaTest {
    @Autowired
    @Qualifier("departmentName")
    private String departmentName;

    @Autowired
    private Department department;

    @Autowired
    private Department persistedDepartment;

    @Bean(name = "department")
    @Scope("prototype")
    Department instantiateValidDepartment() {
        return new Department(context.getBean("departmentName", String.class));
    }

    @Bean(name = "persistedDepartment")
    @Scope("prototype")
    @Autowired
    public Department persistValidDepartment(Department department) {
        uglyPersist(department);

        return department;
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

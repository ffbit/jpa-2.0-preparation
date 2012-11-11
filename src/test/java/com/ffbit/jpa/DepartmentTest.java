package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ffbit.jpa.mock.DepartmentMockFactory;
import com.ffbit.jpa.mock.EmployeeMockFactory;

public class DepartmentTest extends AbstractJpaTest {
    @Autowired
    private DepartmentMockFactory departmentFactory;

    @Autowired
    private EmployeeMockFactory employeeFactory;

    private Department department;
    private Department persistedDepartment;

    @Before
    public void setUp() throws Exception {
        department = departmentFactory.build();
        persistedDepartment = departmentFactory.create();
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        persistFlushAndClean(department);

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

    @Test
    public void itShouldAddAndPersistEmployees() throws Exception {
        department.addEmployee(employeeFactory.build());
        department.addEmployee(employeeFactory.build());

        persistFlushAndClean(department);

        Department persisted = em.find(Department.class, department.getId());

        assertThat("a department should persist its employees", 2, is(persisted
                .getEmployees().size()));
    }

}

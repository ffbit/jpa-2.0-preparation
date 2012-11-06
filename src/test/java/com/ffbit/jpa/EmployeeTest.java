package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ffbit.jpa.mock.EmployeeMockFactory;

@Configuration
public class EmployeeTest extends AbstractJpaTest {
    @Autowired
    private EmployeeMockFactory employeeFactory;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = employeeFactory.build();
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertThat(persisted, is(notNullValue()));
    }

    @Test
    public void itShouldBePersistableWithoutParkingPlace() throws Exception {
        employee.setParkingPlace(null);

        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertThat(persisted, is(notNullValue()));
    }

}

package com.ffbit.jpa;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeTest extends AbstractJpaTest {
    @Autowired
    @Qualifier("employeeName")
    private String employeeName;

    @Autowired
    private BigDecimal salary;

    @Autowired
    private Department persistedDepartment;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee(employeeName, salary, persistedDepartment);
        employee.setParkingPlace(new ParkingPlace(1, "A"));
    }

    @Test
    public void itShoulBePersistable() throws Exception {
        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertNotNull(persisted);
    }

    @Test
    public void itShoulBePersistableWithoutParkingPlace() throws Exception {
        employee.setParkingPlace(null);

        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertNotNull(persisted);
    }

}

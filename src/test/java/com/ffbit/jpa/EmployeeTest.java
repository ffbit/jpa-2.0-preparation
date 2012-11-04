package com.ffbit.jpa;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeTest extends AbstractJpaTest {
    private String name;
    private BigDecimal salary;

    @Autowired
    private Department persistedDepartment;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        name = "Mark Baker";
        salary = new BigDecimal("80000.50");
        employee = new Employee(name, salary, persistedDepartment);
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

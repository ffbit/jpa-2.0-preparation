package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ffbit.jpa.mock.DepartmentMockFactory;
import com.ffbit.jpa.mock.EmployeeMockFactory;
import com.ffbit.jpa.mock.ParkingPlaceMockFactory;
import com.ffbit.jpa.mock.PositionMockFactory;

public class EmployeeTest extends AbstractJpaTest {
    @Autowired
    private EmployeeMockFactory employeeFactory;

    @Autowired
    private DepartmentMockFactory departmentFactory;

    @Autowired
    private ParkingPlaceMockFactory parkingPlaceFactory;

    @Autowired
    private PositionMockFactory positionFactory;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        String name = "John Carter";
        BigDecimal salary = new BigDecimal("10000.05");
        Department department = departmentFactory.create();

        employee = new Employee(name, salary, department);
        employee.setParkingPlace(parkingPlaceFactory.create());
        employee.setPosition(positionFactory.create());
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        em.persist(employee);
        em.flush();
        em.clear();

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

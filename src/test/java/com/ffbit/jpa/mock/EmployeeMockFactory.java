package com.ffbit.jpa.mock;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Department;
import com.ffbit.jpa.Employee;
import com.ffbit.jpa.ParkingPlace;
import com.ffbit.jpa.Position;
import com.github.javafaker.Faker;

@MockRepository
@Transactional
public class EmployeeMockFactory extends MockFactory<Employee> {
    @Autowired
    private Faker faker;

    @Autowired
    private DepartmentMockFactory departmentFactory;

    @Autowired
    private ParkingPlaceMockFactory parkingPlaceFactory;

    @Autowired
    private PositionMockFactory positionFactory;

    @Override
    public Employee build() {
        Employee employee = new Employee(name(), salary(), department());
        employee.setParkingPlace(parkingPlace());
        employee.setPosition(position());

        return employee;
    }

    private String name() {
        return faker.name();
    }

    private BigDecimal salary() {
        return new BigDecimal(faker.numerify("#####.##"));
    }

    private Department department() {
        return departmentFactory.create();
    }

    private ParkingPlace parkingPlace() {
        return parkingPlaceFactory.create();
    }

    private Position position() {
        return positionFactory.create();
    }

}

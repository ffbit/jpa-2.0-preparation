package com.ffbit.jpa.mock;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Department;
import com.ffbit.jpa.Employee;
import com.github.javafaker.Faker;

@MockRepository
@Transactional
public class EmployeeMockFactory extends MockFactory<Employee> {
    @Autowired
    private Faker faker;

    @Autowired
    private DepartmentMockFactory departmentFactory;

    @Override
    public Employee build() {
        return new Employee(name(), salary(), department());
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

}

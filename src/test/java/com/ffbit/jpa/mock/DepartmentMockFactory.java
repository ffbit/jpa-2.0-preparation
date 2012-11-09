package com.ffbit.jpa.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Department;
import com.github.javafaker.Faker;

@MockRepository
@Transactional
public class DepartmentMockFactory extends MockFactory<Department> {
    @Autowired
    private Faker faker;

    @Override
    public Department build() {
        return new Department(createName());
    }

    private String createName() {
        return faker.firstName() + faker.citySuffix() + " Department";
    }

}

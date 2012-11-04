package com.ffbit.jpa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

@Configuration
public class NamesFactory {
    @Autowired
    private Faker faker;

    @Bean
    @Scope("prototype")
    String departmentName() {
        return city() + " Department";
    }

    @Bean
    @Scope("prototype")
    String employeeName() {
        return faker.name();
    }

    @Bean
    @Scope("prototype")
    String city() {
        return faker.firstName() + faker.citySuffix();
    }

}

package com.ffbit.jpa.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

@Configuration
@Scope("prototype")
public class SalaryFactory {
    @Autowired
    private Faker faker;

    @Bean
    BigDecimal salary() {
        return new BigDecimal(faker.numerify("#####.##"));
    }

}

package com.ffbit.jpa.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class SingletonFactory {

    @Bean
    Faker faker() {
        return new Faker();
    }

}

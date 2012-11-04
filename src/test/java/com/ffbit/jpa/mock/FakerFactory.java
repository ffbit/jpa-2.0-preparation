package com.ffbit.jpa.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class FakerFactory {

    @Bean
    Faker faker() {
        return new Faker();
    }

}

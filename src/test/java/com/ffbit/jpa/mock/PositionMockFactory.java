package com.ffbit.jpa.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Position;
import com.github.javafaker.Faker;

@MockRepository
@Transactional
public class PositionMockFactory extends MockFactory<Position> {
    @Autowired
    private Faker faker;

    @Override
    public Position build() {
        return new Position(title());
    }

    private String title() {
        return String.format("%s %s",
                faker.fetchString("position.qualification"),
                faker.fetchString("position.title"));
    }

}

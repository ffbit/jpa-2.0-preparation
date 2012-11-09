package com.ffbit.jpa.mock;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.ParkingPlace;
import com.github.javafaker.Faker;

@MockRepository
@Transactional
public class ParkingPlaceMockFactory extends MockFactory<ParkingPlace> {
    @Autowired
    private Faker faker;

    @Override
    public ParkingPlace build() {
        return new ParkingPlace(lot(), location());
    }

    private Integer lot() {
        return RandomUtils.nextInt(Byte.MAX_VALUE);
    }

    private String location() {
        return faker.letterify("???").toUpperCase();
    }

}

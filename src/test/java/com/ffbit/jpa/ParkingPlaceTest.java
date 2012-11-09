package com.ffbit.jpa;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * JPA integration test for the ParkingPlace entity.
 *
 * @author Dmytro Chyzhykov
 */
public class ParkingPlaceTest extends AbstractJpaTest {
    private ParkingPlace parkingPlace;

    @Test
    public void itShouldBePersitable() throws Exception {
        parkingPlace = new ParkingPlace();
        parkingPlace.setLot(1);
        parkingPlace.setLocation("ABC");

        em.persist(parkingPlace);

        assertThat(parkingPlace, is(notNullValue()));
    }

}

package com.ffbit.jpa;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * JPA integration test for the Position entity.
 *
 * @author Dmytro Chyzhykov
 */
public class PositionTest extends AbstractJpaTest {
    private Position position;

    @Test
    public void itShouldBePersistable() throws Exception {
        String title = "Java Developer";
        position = new Position(title);

        em.persist(position);
        em.flush();
        em.clear();

        Position persisted = em.find(Position.class, position.getId());

        assertThat(persisted, is(notNullValue()));
        assertThat(persisted.getTitle(), equalTo(title));
    }
}

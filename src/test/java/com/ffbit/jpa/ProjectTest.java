package com.ffbit.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ffbit.jpa.mock.EmployeeMockFactory;

public class ProjectTest extends AbstractJpaTest {
    @Autowired
    private EmployeeMockFactory employeeFactory;

    private String name;

    @Before
    public void setUp() throws Exception {
        name = "Automotive";
    }

    @Test
    public void itShouldBePersistable() throws Exception {
        Project project = new Project(name);

        persistFlushAndClean(project);

        Project persisted = em.find(Project.class, project.getId());

        assertThat(persisted, is(notNullValue()));
        assertThat(persisted.getName(), is(equalTo(name)));
    }

    @Test
    public void projectsWithTheSameNameShouldBeEqual() throws Exception {
        Project first = new Project(name);
        Project second = new Project(name);

        assertThat(first, is(equalTo(second)));
    }

}

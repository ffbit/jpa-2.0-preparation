import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Employee;
import com.ffbit.jpa.ParkingPlace;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class EmployeeTest {

    @PersistenceContext
    private EntityManager em;

    private String name;
    private BigDecimal salary;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        name = "Mark Baker";
        salary = new BigDecimal("80000.50");
        employee = new Employee(name, salary);
        employee.setParkingPlace(new ParkingPlace(1, "A"));
    }

    @Test
    public void itShoulBePersistable() throws Exception {
        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertNotNull(persisted);
    }

    @Test
    public void itShoulBePersistableWithoutParkingPlace() throws Exception {
        employee.setParkingPlace(null);

        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertNotNull(persisted);
    }
}

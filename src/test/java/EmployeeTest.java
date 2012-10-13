import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ffbit.jpa.Employee;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class EmployeeTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void itShoulBePersistable() throws Exception {
        Employee employee = new Employee("Mark Baker", new BigDecimal(1e5));

        em.persist(employee);

        Employee persisted = em.find(Employee.class, employee.getId());

        assertNotNull(persisted);
    }
}

package tonneltromb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tonneltromb.domain.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BasicEmployeeRepository implements EmployeeRepositoryInterface {

private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.manager = entityManager;
    }

    public Employee getEmployeeById(int id) {
       return manager.find(Employee.class, id);
    }

    public int addEmployee(Employee employee) {
        manager.persist(employee);
        return employee.getId();
    }

    public void editEmployee(Employee employee) {
        manager.merge(employee);
    }

    public void removeEmployeeById(int id) {
        manager
                .createQuery("delete Employee where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Employee> getEmployees() {
        TypedQuery<Employee> query = manager
                .createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

}



package tonneltromb.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import tonneltromb.domain.Employee;
import tonneltromb.utils.HibernateSessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface {

    private SessionFactory sessionFactory =
            HibernateSessionFactory.getSessionFactory();

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        return session.find(Employee.class, id);
    }

    public int addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.save(employee);
        return employee.getId();
    }


    public void editEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.update(employee);
    }

    public void removeEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        session
                .createQuery("delete Employee where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        TypedQuery<Employee> query = session
                .createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

}



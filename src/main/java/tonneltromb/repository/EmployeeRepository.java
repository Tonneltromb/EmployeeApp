package tonneltromb.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import tonneltromb.model.Employee;
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
        session.getTransaction().begin();
        session.save(employee);
        session.getTransaction().commit();
        return employee.getId();
    }

    public void editEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(employee);
        session.getTransaction().commit();
    }

    public void removeEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session
                .createQuery("delete Employee where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        TypedQuery<Employee> query = session
                .createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

}



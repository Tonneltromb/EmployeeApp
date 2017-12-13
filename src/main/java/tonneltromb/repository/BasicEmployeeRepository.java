package tonneltromb.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tonneltromb.domain.Employee;
import tonneltromb.utils.HibernateSessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class BasicEmployeeRepository implements EmployeeRepositoryInterface {

    private SessionFactory sessionFactory =
            HibernateSessionFactory.getSessionFactory();

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        return session.find(Employee.class, id);
    }
@Transactional
    public int addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
//        session.getTransaction().begin();
        session.save(employee);
//        session.getTransaction().commit();
        return employee.getId();
    }
@Transactional
    public void editEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
//        session.getTransaction().begin();
        session.update(employee);
//        session.getTransaction().commit();
    }
@Transactional
    public void removeEmployeeById(int id) {
        Session session = sessionFactory.openSession();
       // session.getTransaction().begin();
        session
                .createQuery("delete Employee where id=:id")
                .setParameter("id", id)
                .executeUpdate();
       // session.getTransaction().commit();
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        TypedQuery<Employee> query = session
                .createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

}



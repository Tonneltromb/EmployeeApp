package tonneltromb.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
import tonneltromb.utils.HibernateSessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository

public class EmployeeRepository implements EmployeeServiceIF<Employee>, PositionServiceIF<Position> {

    private SessionFactory sessionFactory;

    @Autowired
    public void getSessionFactory() {
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();

    }

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

    public List<Position> positionsList() {
        Session session = sessionFactory.openSession();
        TypedQuery<Position> query = session
                .createQuery("from Position", Position.class);
        return query.getResultList();
    }
}



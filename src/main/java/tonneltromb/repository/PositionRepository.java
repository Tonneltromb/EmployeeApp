package tonneltromb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import tonneltromb.model.Employee;
import tonneltromb.model.Position;
import tonneltromb.utils.HibernateSessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PositionRepository implements PositionRepositoryInterface {

    private SessionFactory sessionFactory =
            HibernateSessionFactory.getSessionFactory();

    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        return session.find(Employee.class, id);
    }

    public List<Position> getPositionsList() {
        Session session = sessionFactory.openSession();
        TypedQuery<Position> query = session
                .createQuery("from Position", Position.class);
        return query.getResultList();
    }
}

package tonneltromb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
=======
import tonneltromb.model.Employee;
import tonneltromb.model.Position;
>>>>>>> 5aa3ac90daa6daf1fc40be53168d45f45d872c2d
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

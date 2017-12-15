package tonneltromb.repository;

import org.springframework.stereotype.Repository;
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BasicPositionRepository implements PositionRepositoryInterface {

    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.manager = entityManager;
    }

    public Employee getEmployeeById(int id) {
        return manager.find(Employee.class, id);
    }

    public List<Position> getPositions() {
        TypedQuery<Position> query = manager
                .createQuery("from Position", Position.class);
        return query.getResultList();
    }
}

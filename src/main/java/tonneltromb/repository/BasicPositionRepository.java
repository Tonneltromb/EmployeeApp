package tonneltromb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tonneltromb.domain.Position;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BasicPositionRepository implements PositionRepository {

    private EntityManager manager;

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.manager = entityManager;
    }

    public List<Position> getPositions() {
        TypedQuery<Position> query = manager
                .createQuery("from Position", Position.class);
        return query.getResultList();
    }
}

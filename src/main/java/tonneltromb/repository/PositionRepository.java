package tonneltromb.repository;
import tonneltromb.domain.Position;

import java.util.List;

public interface PositionRepository {
    List<Position> getPositions();
}

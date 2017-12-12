package tonneltromb.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.domain.Position;

import java.util.List;


public interface ControllerInterface {

    ResponseEntity<List<ContractEmployee>> getEmployees();

    void removeEmployee(int id);

    ResponseEntity<Integer> addEmployee(ContractEmployee contractEmployee);

    ResponseEntity editEmployee(ContractEmployee contractEmployee);

    ResponseEntity<List<Position>> getPositions();
}

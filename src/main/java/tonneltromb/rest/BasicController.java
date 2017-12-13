package tonneltromb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.service.EmployeeServiceInterface;
import tonneltromb.domain.Position;
import java.util.List;


public class BasicController implements ControllerInterface {

    private EmployeeServiceInterface service;

    public EmployeeServiceInterface getService() {
        return service;
    }

    @Autowired
    public void setService(EmployeeServiceInterface service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ContractEmployee>> getEmployees() {
        List<ContractEmployee> list = service.getEmployeesCollection();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<Integer> addEmployee(ContractEmployee contractEmployee) {
        return new ResponseEntity<>(service.addEmployee(contractEmployee),HttpStatus.OK);
    }

    @Override
    public ResponseEntity editEmployee(ContractEmployee contractEmployee) {
        service.editEmployee(contractEmployee);
        return null;
    }

    @Override
    public ResponseEntity removeEmployee(@RequestParam int id) {
        service.removeEmployeeById(id);
        return null;
    }

    @Override
    public ResponseEntity<List<Position>> getPositions() {
        List<Position> list = service.getPositions();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(list);
    }
}

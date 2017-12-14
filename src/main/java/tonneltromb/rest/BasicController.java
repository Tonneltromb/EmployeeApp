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

@RestController
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
        List<ContractEmployee> list = service.getEmployees();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity addEmployee(ContractEmployee contractEmployee) {
        try {
            return ResponseEntity.ok(service.addEmployee(contractEmployee));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity editEmployee(ContractEmployee contractEmployee, @RequestParam int id) {
        try {
            contractEmployee.setId(id);
            service.editEmployee(contractEmployee);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity removeEmployee(@RequestParam int id) {
        try {
            service.removeEmployeeById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

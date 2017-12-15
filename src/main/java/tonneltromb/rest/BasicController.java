package tonneltromb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.service.EmployeeService;
import tonneltromb.domain.Position;

import java.util.List;

@Component
public class BasicController implements Controller {

    private EmployeeService service;

    public EmployeeService getService() {
        return service;
    }

    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<ContractEmployee>> getEmployees() {
        try {
            List<ContractEmployee> list = service.getEmployees();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity editEmployee(ContractEmployee contractEmployee) {
        try {
            service.editEmployee(contractEmployee);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity removeEmployee(@PathVariable int id) {
        try {
            service.removeEmployeeById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Position>> getPositions() {
        try {
            List<Position> list = service.getPositions();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

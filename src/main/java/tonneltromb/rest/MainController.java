package tonneltromb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.service.EmployeeServiceInterface;
import tonneltromb.domain.Position;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MainController implements ControllerInterface {


    private EmployeeServiceInterface service;

    public EmployeeServiceInterface getService() {
        return service;
    }

    @Autowired
    public void setService(EmployeeServiceInterface service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/employees/showAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ContractEmployee>> getEmployees() {
        List<ContractEmployee> list = service.getEmployeesCollection();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> addEmployee(ContractEmployee contractEmployee) {
        return new ResponseEntity<>(service.addEmployee(contractEmployee),HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity editEmployee(ContractEmployee contractEmployee) {
        service.editEmployee(contractEmployee);
        return null;
    }

    @Override
    @RequestMapping(value = "/employees/remove", method = RequestMethod.GET)
    public ResponseEntity removeEmployee(@RequestParam int id) {
        service.removeEmployeeById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/positions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Position>> getPositions() {
        List<Position> list = service.getPositions();
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}

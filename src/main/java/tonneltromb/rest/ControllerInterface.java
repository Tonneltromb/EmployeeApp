package tonneltromb.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.domain.Position;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public interface ControllerInterface {

    @RequestMapping(value = "/employees/showAll", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ContractEmployee>> getEmployees();

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity addEmployee(ContractEmployee contractEmployee);

    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity editEmployee(ContractEmployee contractEmployee, @RequestParam int id);

    @RequestMapping(value = "/employees/remove", method = RequestMethod.GET)
    ResponseEntity removeEmployee(int id);

    @RequestMapping(value = "/positions", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Position>> getPositions();
}

package tonneltromb.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.domain.Position;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public interface Controller {

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ContractEmployee>> getEmployees();

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity addEmployee(ContractEmployee contractEmployee);

    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity editEmployee(ContractEmployee contractEmployee);

    @RequestMapping(value = "/employees/remove/{id}", method = RequestMethod.DELETE)
    ResponseEntity removeEmployee(@PathVariable Integer id);

    @RequestMapping(value = "/positions", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Position>> getPositions();
}

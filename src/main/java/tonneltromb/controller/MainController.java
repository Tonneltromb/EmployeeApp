package tonneltromb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
import tonneltromb.repository.EmployeeRepository;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private EmployeeRepository repository;

    @RequestMapping(value = "/employees/showAll", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> getAllEmployees() {
        return repository.getAllEmployees();
    }

    @RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = "application/json")
    public int addEmployee(@RequestParam String name,
                           @RequestParam String lastName,
                           @RequestParam String pass,
                           @RequestParam String dateOfEmployment,
                           @RequestParam int positionId) throws ParseException {
        return repository.addEmployee(
                new Employee(name, lastName, positionId, pass, dateOfEmployment));
    }

    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST, produces = "application/json")
    public void editEmployee(@RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam String pass,
                             @RequestParam String dateOfEmployment,
                             @RequestParam int positionId,
                             @RequestParam int id) throws ParseException {
        Employee employee = new Employee(name, lastName, positionId, pass, dateOfEmployment);
        employee.setId(id);
        repository.editEmployee(employee);
    }

    @RequestMapping(value = "/employees/remove", method = RequestMethod.GET)
    public void removeEmployee(@RequestParam int id) {
        repository.removeEmployeeById(id);
    }


    @RequestMapping(value = "/positions", method = RequestMethod.GET, produces = "application/json")
    public List<Position> getPositionList() {
        return repository.positionsList();
    }
}

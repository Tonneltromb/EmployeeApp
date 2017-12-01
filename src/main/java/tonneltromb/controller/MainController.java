package tonneltromb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tonneltromb.contract.ContractEmployee;
import tonneltromb.contract.ContractEmployeeService;
import tonneltromb.domain.Position;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private ContractEmployeeService service;

    @RequestMapping(
            value = "/employees/showAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public Map<Integer, ContractEmployee> getAllEmployees() {
        return service.getEmployeesCollection();
    }

    @RequestMapping(
            value = "/employees/add",
            method = RequestMethod.POST,
            produces = "application/json")
    public int addEmployee(ContractEmployee contractEmployee) {
        System.out.println(contractEmployee);
        return service.addEmployee(contractEmployee);
    }

    @RequestMapping(
            value = "/employees/edit",
            method = RequestMethod.POST,
            produces = "application/json")
    public void editEmployee(ContractEmployee contractEmployee,
                             @RequestParam int id) {
        service.editEmployee(contractEmployee, id);
    }

    @RequestMapping(
            value = "/employees/remove",
            method = RequestMethod.GET)
    public void removeEmployee(@RequestParam int id) {
        service.removeEmployeeById(id);
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET, produces = "application/json")
    public List<Position> getPositionList() {
        return service.positionsList();
    }
}

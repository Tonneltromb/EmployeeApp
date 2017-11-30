package tonneltromb.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
import tonneltromb.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractEmployeeService implements ContractEmployeeServiceInterface {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public int addEmployee(ContractEmployee contractEmployee) {
        Employee employee = new Employee(
                contractEmployee.getName(),
                contractEmployee.getLastName(),
                contractEmployee.getPositionId(),
                contractEmployee.getPass(),
                contractEmployee.getDateOfEmployment());
        repository.addEmployee(employee);
        return repository.addEmployee(employee);
    }

    @Override
    public void removeEmployeeById(int id) {
        repository.removeEmployeeById(id);
    }

    @Override
    public void editEmployee(ContractEmployee contractEmployee, int id) {
        Employee employee = new Employee(
                contractEmployee.getName(),
                contractEmployee.getLastName(),
                contractEmployee.getPositionId(),
                contractEmployee.getPass(),
                contractEmployee.getDateOfEmployment());
        employee.setId(id);
        repository.editEmployee(employee);
    }

    @Override
    public Map<Integer, ContractEmployee> getEmployeesCollection() {
        List<Employee> employees = repository.getAllEmployees();
        Map<Integer,ContractEmployee> contractEmployeesList = new HashMap<>();
        for (Employee employee : employees){
            ContractEmployee contractEmployee = new ContractEmployee(
                    employee.getName(),
                    employee.getLastName(),
                    employee.getPositionId(),
                    employee.getPass(),
                    employee.getDateOfEmployment()
            );
            contractEmployeesList.put(employee.getId(),contractEmployee);
        }
        return contractEmployeesList;
    }

    @Override
    public ContractEmployee getEmployeeById(int id) {
        Employee employee = repository.getEmployeeById(id);
        ContractEmployee contractEmployee = new ContractEmployee(
                employee.getName(),
                employee.getLastName(),
                employee.getPositionId(),
                employee.getPass(),
                employee.getDateOfEmployment()
        );
        return contractEmployee;
    }

    public List<Position> positionsList() {
        return repository.getPositionsList();
    }
}

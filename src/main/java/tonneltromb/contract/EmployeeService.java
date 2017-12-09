package tonneltromb.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tonneltromb.model.Employee;
import tonneltromb.model.Position;
import tonneltromb.repository.EmployeeRepository;
import tonneltromb.repository.EmployeeRepositoryInterface;
import tonneltromb.repository.PositionRepositoryInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private EmployeeRepositoryInterface employeeRepository;

    private PositionRepositoryInterface positionRepository;

    public PositionRepositoryInterface getPositionRepository() {
        return positionRepository;
    }

    public EmployeeRepositoryInterface getEmployeeRepository() {
        return employeeRepository;
    }

    @Autowired
    public void setPositionRepository(PositionRepositoryInterface positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public int addEmployee(ContractEmployee contractEmployee) {
        Employee employee = contractToModel(contractEmployee);
        return employeeRepository.addEmployee(employee);
    }

    @Override
    public void removeEmployeeById(int id) {
        employeeRepository.removeEmployeeById(id);
    }

    @Override
    public void editEmployee(ContractEmployee contractEmployee, int id) {
        Employee employee = contractToModel(contractEmployee);
        employee.setId(id);
        employeeRepository.editEmployee(employee);
    }

    @Override
    public Map<Integer, ContractEmployee> getEmployeesCollection() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        Map<Integer,ContractEmployee> contractEmployeesList = new HashMap<>();
        for (Employee employee : employees){
            ContractEmployee contractEmployee = modelToContract(employee);
            contractEmployeesList.put(employee.getId(),contractEmployee);
        }
        return contractEmployeesList;
    }

    @Override
    public ContractEmployee getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        return modelToContract(employee);
    }

    @Override
    public List<Position> getPositions() {
        return positionRepository.getPositionsList();
    }

    private ContractEmployee modelToContract(Employee model){
        return new ContractEmployee(
                model.getFirstName(),
                model.getLastName(),
                model.getPositionId(),
                model.getPass(),
                model.getDateOfEmployment().getTime());
    }

    private Employee contractToModel(ContractEmployee contract) {
        return new Employee(
                contract.getName(),
                contract.getLastName(),
                contract.getPositionId(),
                contract.getPass(),
                contract.getDateOfEmployment());
    }

}

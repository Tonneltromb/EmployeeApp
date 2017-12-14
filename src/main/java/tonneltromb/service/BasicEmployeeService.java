package tonneltromb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.domain.Employee;
import tonneltromb.domain.Position;
import tonneltromb.repository.EmployeeRepositoryInterface;
import tonneltromb.repository.PositionRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicEmployeeService implements EmployeeServiceInterface {

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
    public void editEmployee(ContractEmployee contractEmployee) {

        Employee employee = contractToModel(contractEmployee);
        employee.setId(contractEmployee.getId());
        employeeRepository.editEmployee(employee);
    }

    @Override
    public List<ContractEmployee> getEmployees() {
        List<Employee> employees = employeeRepository.getEmployees();
        List<ContractEmployee> contractEmployeesList = new ArrayList<>();
        for (Employee employee : employees){
            ContractEmployee contractEmployee = modelToContract(employee);
            contractEmployeesList.add(contractEmployee);
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
        return positionRepository.getPositions();
    }

    private ContractEmployee modelToContract(Employee model){
        ContractEmployee employee = new ContractEmployee(
                model.getFirstName(),
                model.getLastName(),
                model.getPositionId(),
                model.getPass(),
                model.getDateOfEmployment().getTime());
        employee.setId(model.getId());
        return employee;
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

package tonneltromb.service;

import tonneltromb.rest.contract.ContractEmployee;
import tonneltromb.domain.Position;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {

    int addEmployee(ContractEmployee contractEmployee);
    void removeEmployeeById(int id);
    void editEmployee(ContractEmployee contractEmployee);
    ContractEmployee getEmployeeById(int id);
    List<ContractEmployee> getEmployees();
    List<Position> getPositions();
}

package tonneltromb.service;

import tonneltromb.domain.Position;
import tonneltromb.rest.contract.ContractEmployee;
import java.util.List;

public interface EmployeeService {

    int addEmployee(ContractEmployee contractEmployee);
    void removeEmployeeById(int id);
    void editEmployee(ContractEmployee contractEmployee);
    ContractEmployee getEmployeeById(int id);
    List<ContractEmployee> getEmployees();
    List<Position> getPositions();
}

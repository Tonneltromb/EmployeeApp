package tonneltromb.contract;

import tonneltromb.model.Position;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceInterface {

    int addEmployee(ContractEmployee contractEmployee);
    void removeEmployeeById(int id);
    void editEmployee(ContractEmployee contractEmployee, int id);
    ContractEmployee getEmployeeById(int id);
    Map<Integer, ContractEmployee> getEmployeesCollection();
    List<Position> getPositions();
}

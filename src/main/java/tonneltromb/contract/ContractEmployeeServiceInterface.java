package tonneltromb.contract;

import java.util.Map;

public interface ContractEmployeeServiceInterface {

    int addEmployee(ContractEmployee contractEmployee);
    void removeEmployeeById(int id);
    void editEmployee(ContractEmployee contractEmployee, int id);
    ContractEmployee getEmployeeById(int id);
    Map<Integer, ContractEmployee> getEmployeesCollection();
}

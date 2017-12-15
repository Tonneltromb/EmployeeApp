package tonneltromb.repository;

import tonneltromb.domain.Employee;
import java.util.List;

public interface EmployeeRepository {

    Employee getEmployeeById(int value);

    int addEmployee(Employee employee);

    void updateEmployee(Employee e);

    void removeEmployeeById(int value);

    List<Employee> getEmployees();
}

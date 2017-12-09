package tonneltromb.repository;

import tonneltromb.model.Employee;

import java.util.List;

public interface EmployeeRepositoryInterface {

    Employee getEmployeeById(int value);

    int addEmployee(Employee employee);

    void editEmployee(Employee e);

    void removeEmployeeById(int value);

    List<Employee> getAllEmployees();
}

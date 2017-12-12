package tonneltromb.repository;

<<<<<<< HEAD
import tonneltromb.domain.Employee;
=======
import tonneltromb.model.Employee;
>>>>>>> 5aa3ac90daa6daf1fc40be53168d45f45d872c2d

import java.util.List;

public interface EmployeeRepositoryInterface {

    Employee getEmployeeById(int value);

    int addEmployee(Employee employee);

    void editEmployee(Employee e);

    void removeEmployeeById(int value);

    List<Employee> getAllEmployees();
}

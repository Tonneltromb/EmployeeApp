package tonneltromb.repository;

import java.util.List;

public interface EmployeeServiceIF<E> {

    E getEmployeeById(int value);

    int addEmployee(E e);

    void editEmployee(E e);

    void removeEmployeeById(int value);

    List<E> getAllEmployees();
}

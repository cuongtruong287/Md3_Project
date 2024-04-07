package DAO;

import ENTITY.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployee();
    boolean addEmployee(Employee employee);

    //Employee findEmployeeById(String emp_Id);

    //boolean updateEmployee(Employee employee);

    //List<Employee> searchEmployeeByName(String keyword);

    //boolean updateEmployeeStatus(Employee employee);
}

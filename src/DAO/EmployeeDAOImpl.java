package DAO;

import ENTITY.Employee;
import UTIL.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployee() {
        // mo ket noi
        Connection connection = ConnectionDB.openConnection();
        List<Employee> employees = new ArrayList<>();
        // thuc hien truy van cau lenh sql thong qua
        try {
            String sqlSelectAllEmployee = "select * from EMPLOYEE";
            PreparedStatement statement = connection.prepareStatement(sqlSelectAllEmployee);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmp_Id(resultSet.getString("emp_Id"));
                employee.setEmp_Name(resultSet.getString("emp_Name"));
                employee.setBirth_Of_Date(resultSet.getDate("birth_Of_Date"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmp_Status(resultSet.getBoolean("emp_Status"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return employees;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        Connection connection = ConnectionDB.openConnection();
        String sqlAddEmployee = "insert into EMPLOYEE(emp_Id, emp_Name, birth_Of_Date, email, phone, address, emp_Status) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlAddEmployee);
            statement.setString(1,employee.getEmp_Id());
            statement.setString(2,employee.getEmp_Name());
            statement.setDate(3,employee.getBirth_Of_Date());
            statement.setString(4,employee.getEmail());
            statement.setString(5,employee.getPhone());
            statement.setString(6,employee.getAddress());
            statement.setBoolean(7,employee.getEmp_Status());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }
}

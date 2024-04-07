package PROGRAM;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import ENTITY.Employee;
import ENTITY.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class EmployeeProgram {
    public static void main(String[] args) throws ParseException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                    1. Danh sách nhân viên
                    2. Thêm mới nhân viên
                    3. Cập nhật thông tin nhân viên 
                    4. Cập nhật trạng thái nhân viên 
                    5. Tìm kiếm nhân viên
                    6. Thoát""");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Employee> employees = employeeDAO.getAllEmployee();
                    for (Employee employee : employees) {
                        System.out.println(employee.toString());
                    }
                    break;
                case 2:
                    Employee employee = new Employee();
                    System.out.println("MOi ban nhap thong tin nhan vien:");
                    employee.inputDataEmployee(scanner);
                    if (employeeDAO.addEmployee(employee)) {
                        System.out.println("Them nhan vien moi thanh cong!");
                    } else {
                        System.err.println("Loi, vui long nhap lai!");
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Loi, vui long chon lai!");

            }
        } while (true);
    }
}

package ENTITY;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Employee {
    private String emp_Id;
    private String emp_Name;
    private Date birth_Of_Date;
    private String email;
    private String phone;
    private String address;
    private Boolean emp_Status;

    public Employee() {
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public java.sql.Date getBirth_Of_Date() {
        return (java.sql.Date) birth_Of_Date;
    }

    public void setBirth_Of_Date(Date birth_Of_Date) {
        this.birth_Of_Date = birth_Of_Date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEmp_Status() {
        return emp_Status;
    }

    public void setEmp_Status(Boolean emp_Status) {
        this.emp_Status = emp_Status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_Id='" + emp_Id + '\'' +
                ", emp_Name='" + emp_Name + '\'' +
                ", birth_Of_Date=" + birth_Of_Date +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", emp_Status=" + emp_Status +
                '}';
    }

    Date scanToDate(String input) throws ParseException {
        try (Scanner scanner = new Scanner(input)) {
            String dateString = scanner.next();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateString);
        }
    }
    public void inputDataEmployee(Scanner scanner) throws ParseException {
        System.out.println("Nhap vao ten ma nhan vien");
        emp_Id = scanner.nextLine();
        System.out.println("Nhap vao ten nhan vien");
        emp_Name = scanner.nextLine();
        System.out.println("Nhap vao ngay thang nam sinh nhan vien");
        birth_Of_Date = scanToDate(scanner.nextLine());
        System.out.println("Nhap vao email nhan vien");
        email = scanner.nextLine();
        System.out.println("Nhap vao sdt nhan vien");
        phone = scanner.nextLine();
        System.out.println("Nhap vao dia chi nhan vien");
        address = scanner.nextLine();
        System.out.println("Nhap vao trang thai nhan vien");
        emp_Status = Boolean.parseBoolean(scanner.nextLine());
    }
}


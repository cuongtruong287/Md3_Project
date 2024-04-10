package ENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String product_Id;
    private String product_Name;
    private String manufacturer;
    private Date created;
    private int batch;
    private int product_Quantity;
    private Boolean product_Status;

    public Product() {
    }

    ;

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getProduct_Quantity() {
        return product_Quantity;
    }

    public void setProduct_Quantity(int product_Quantity) {
        this.product_Quantity = product_Quantity;
    }

    public Boolean getProduct_Status() {
        return product_Status;
    }

    public void setProduct_Status(Boolean product_Status) {
        this.product_Status = product_Status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_Id=" + product_Id +
                ", product_Name='" + product_Name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", created=" + created +
                ", batch=" + batch +
                ", product_Quantity=" + product_Quantity +
                ", product_Status=" + product_Status +
                '}';
    }

    public void productInputData(Scanner scanner) {
        System.out.println("Nhap vao ma san pham");
        product_Id = scanner.nextLine();
        System.out.println("Nhap vao ten san pham:");
        product_Name = scanner.nextLine();
        System.out.println("Nhap vao ten nha san xuat:");
        manufacturer = scanner.nextLine();
        System.out.println("Nhap vao lo chua san pham:");
        batch = Integer.parseInt(scanner.nextLine());
        System.out.println("Moi ban nhap so luong hang:");
        product_Quantity = Integer.parseInt(scanner.nextLine());
    }

    public void inputCreated(Scanner scanner) {
        System.out.println("Mời bạn nhập vào ngày tạo");
        while (true) {
            String inputCreated = scanner.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                this.created = format.parse(inputCreated);
                break;
            } catch (Exception e) {
                System.err.println("Sai định dạng");;
            }
        }
    }
}

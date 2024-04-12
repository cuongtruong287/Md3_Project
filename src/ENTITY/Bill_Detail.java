package ENTITY;

import java.util.Scanner;

public class Bill_Detail {
    private int bill_Detail_Id;
    private int bill_Id;
    private String product_Id;
    private int quantity_Bill;
    private float price;

    public Bill_Detail() {
    }

    public Bill_Detail(int bill_Detail_Id, int bill_Id, String product_Id, int quantity_Bill, float price) {
        this.bill_Detail_Id = bill_Detail_Id;
        this.bill_Id = bill_Id;
        this.product_Id = product_Id;
        this.quantity_Bill = quantity_Bill;
        this.price = price;
    }

    public int getBill_Detail_Id() {
        return bill_Detail_Id;
    }

    public void setBill_Detail_Id(int bill_Detail_Id) {
        this.bill_Detail_Id = bill_Detail_Id;
    }

    public int getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(int bill_Id) {
        this.bill_Id = bill_Id;
    }

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public int getQuantity_Bill() {
        return quantity_Bill;
    }

    public void setQuantity_Bill(int quantity_Bill) {
        this.quantity_Bill = quantity_Bill;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bill_Detail{" +
                "bill_Detail_Id=" + bill_Detail_Id +
                ", bill_Id=" + bill_Id +
                ", product_Id='" + product_Id + '\'' +
                ", quantity_Bill=" + quantity_Bill +
                ", price=" + price +
                '}';
    }

    public void inputData(Scanner scanner){
        System.out.println("Moi ban nhap ma hoa don");
        bill_Id = Integer.parseInt(scanner.nextLine());
        System.out.println("Moi ban nhap ma san pham");
        product_Id = scanner.nextLine();
        System.out.println("Moi ban nhap so luong hoa don");
        quantity_Bill = Integer.parseInt(scanner.nextLine());
        System.out.println("Moi ban nhap gia");
        price = Float.parseFloat(scanner.nextLine());
    }
}

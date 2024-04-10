package ENTITY;

import java.util.Date;
import java.util.Scanner;

public class Bill {
    private int bill_Id;
    private String bill_Code;
    private Boolean bill_Type;
    private int account_Id_Created;
    private Date bill_Created;
    private Date bill_Auth_Date;
    private int bill_Status;

    public Bill() {
    }

    public Bill(int bill_Id, String bill_Code, Boolean bill_Type, int account_Id_Created, Date bill_Created, Date bill_Auth_Date, int bill_Status) {
        this.bill_Id = bill_Id;
        this.bill_Code = bill_Code;
        this.bill_Type = bill_Type;
        this.account_Id_Created = account_Id_Created;
        this.bill_Created = bill_Created;
        this.bill_Auth_Date = bill_Auth_Date;
        this.bill_Status = bill_Status;
    }

    public int getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(int bill_Id) {
        this.bill_Id = bill_Id;
    }

    public String getBill_Code() {
        return bill_Code;
    }

    public void setBill_Code(String bill_Code) {
        this.bill_Code = bill_Code;
    }

    public Boolean getBill_Type() {
        return bill_Type;
    }

    public void setBill_Type(Boolean bill_Type) {
        this.bill_Type = bill_Type;
    }

    public int getAccount_Id_Created() {
        return account_Id_Created;
    }

    public void setAccount_Id_Created(int account_Id_Created) {
        this.account_Id_Created = account_Id_Created;
    }

    public Date getBill_Created() {
        return bill_Created;
    }

    public void setBill_Created(Date bill_Created) {
        this.bill_Created = bill_Created;
    }

    public Date getBill_Auth_Date() {
        return bill_Auth_Date;
    }

    public void setBill_Auth_Date(Date bill_Auth_Date) {
        this.bill_Auth_Date = bill_Auth_Date;
    }

    public int getBill_Status() {
        return bill_Status;
    }

    public void setBill_Status(int bill_Status) {
        this.bill_Status = bill_Status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bill_Id=" + bill_Id +
                ", bill_Code='" + bill_Code + '\'' +
                ", bill_Type=" + bill_Type +
                ", account_Id_Created=" + account_Id_Created +
                ", bill_Created=" + bill_Created +
                ", bill_Auth_Date=" + bill_Auth_Date +
                ", bill_Status=" + bill_Status +
                '}';
    }

    public void InputData(Scanner scanner){
        System.out.println("Nhap vao ma bill");
        bill_Code = scanner.nextLine();
        System.out.println("Nhap ma tai khoan nguoi thao tac");
        account_Id_Created = Integer.parseInt(scanner.nextLine());
    }
}

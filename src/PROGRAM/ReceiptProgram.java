package PROGRAM;

import DAO.*;
import ENTITY.*;

import java.util.List;
import java.util.Scanner;

public class ReceiptProgram {
    public void runReceipt() {
        Scanner scanner = new Scanner(System.in);
        BillDAO billDAO = new BillDAOImpl();
        do {
            System.out.println("===QUAN LY PHIEU NHAP===");
            System.out.println("""
                                1. Danh sách phiếu nhập
                                2. Tạo phiếu nhập
                                3. Cập nhật thông tin phiếu nhập
                                4. Chi tiết phiếu nhập
                                5. Duyệt phiếu nhập
                                6. Tìm kiếm phiếu nhập
                                7. Thoát
                    """);
            System.out.println("Moi ban nhap:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Bill> bills = billDAO.getReceiptList();
                    for (Bill bill: bills){
                        System.out.println(bill.toString());
                    }
                    break;
                case 2:
                    Bill bill = new Bill();
                    System.out.println("Moi ban nhap thong tin phieu nhap");
                    bill.InputData(scanner);
                    if (billDAO.addReceiptBill(bill)){
                        System.out.println("Them phieu nhap thanh cong!");
                    } else {
                        System.err.println("Loi! Vui long nhap lai!!!");
                    }
                    break;
                case 3:
                    System.out.println("Moi ban nhap ID phieu nhap can cap nhat");
                    int bill_Id = Integer.parseInt(scanner.nextLine());
                    Bill billUpdate = billDAO.findBillById(bill_Id);
                    if (billUpdate == null){
                        System.err.println("Rat tiec, khong tim thay ID phieu nhap can cap nhat!");
                    } else if (!billUpdate.getBill_Type()) {
                        System.err.println("Rat tiec, ma ID ban vua nhap la phieu xuat");
                    }
                    else if (billUpdate.getBill_Status() == 2){
                        System.err.println("Rat tiec, phieu nhap dang o trang thai duyet, khong duoc thao tac");
                    } else {
                        System.out.println("Co phai ban muon thay doi thong tin phieu nhap nay khong?");
                        System.out.println(billUpdate.toString());
                        System.out.println("Xin moi nhap thong tin moi:");
                        System.out.println("Nhap vao ma code phieu nhap:");
                        billUpdate.setBill_Type(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.println("Nhap vao ma tai khoan nguoi chinh sua:");
                        billUpdate.setAccount_Id_Created(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Moi ban nhap trang thai phieu nhap: 0 - Tao, 1 - Huy, 2 - Duyet");
                        billUpdate.setBill_Status(Integer.parseInt(scanner.nextLine()));
                        if (billDAO.updateBill(billUpdate)){
                            System.out.println("Cap nhat phieu nhap thanh cong!!!");
                            System.out.println(billUpdate.toString());
                        }
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Loi! Vui long nhap lai!");
                    break;
            }
        } while (true);

    }
}

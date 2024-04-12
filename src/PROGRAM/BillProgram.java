package PROGRAM;

import DAO.BillDAO;
import DAO.BillDAOImpl;
import ENTITY.Bill;
import ENTITY.Bill_Detail;
import DAO.Bill_DetailDAO;
import DAO.Bill_DetailDAOImpl;
import java.util.List;
import java.util.Scanner;

public class BillProgram {
    public void runBill() {
        Scanner scanner = new Scanner(System.in);
        BillDAO billDAO = new BillDAOImpl();
        Bill_DetailDAO bill_detailDAO = new Bill_DetailDAOImpl();
        do {
            System.out.println("===QUAN LY PHIEU XUAT===");
            System.out.println("""
                                1. Danh sách phiếu xuất
                                2. Tạo phiếu xuất
                                3. Cập nhật thông tin phiếu xuất
                                4. Chi tiết phiếu xuất
                                5. Duyệt phiếu xuất
                                6. Tìm kiếm phiếu xuất
                                7. Thoát
                    """);
            System.out.println("Moi ban nhap:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Bill> bills = billDAO.getBillList();
                    for (Bill bill: bills){
                        System.out.println(bill.toString());
                    }
                    break;
                case 2:
                    Bill bill = new Bill();
                    System.out.println("Moi ban nhap thong tin phieu xuat");
                    bill.InputData(scanner);
                    if (billDAO.addBill(bill)){
                        System.out.println("Them phieu xuat thanh cong!");
                    } else {
                        System.err.println("Loi! Vui long nhap lai!!!");
                    }
                    break;
                case 3:
                    System.out.println("Moi ban nhap ID phieu xuat can cap nhat");
                    int bill_Id = Integer.parseInt(scanner.nextLine());
                    Bill billUpdate = billDAO.findBillById(bill_Id);
                    if (billUpdate == null){
                        System.err.println("Rat tiec, khong tim thay ID phieu nhap can cap nhat!");
                    } else if (billUpdate.getBill_Type()) {
                        System.err.println("Rat tiec, ma ID ban vua nhap la phieu nhap");
                    }
                    else if (billUpdate.getBill_Status() == 2){
                        System.err.println("Rat tiec, phieu xuat dang o trang thai duyet, khong duoc thao tac");
                    } else {
                        System.out.println("Co phai ban muon thay doi thong tin phieu xuat nay khong?");
                        System.out.println(billUpdate.toString());
                        System.out.println("Xin moi nhap thong tin moi:");
                        System.out.println("Nhap vao ma code phieu xuat:");
                        billUpdate.setBill_Type(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.println("Nhap vao ma tai khoan nguoi chinh sua:");
                        billUpdate.setAccount_Id_Created(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Moi ban nhap trang thai phieu xuat: 0 - Tao, 1 - Huy, 2 - Duyet");
                        billUpdate.setBill_Status(Integer.parseInt(scanner.nextLine()));
                        if (billDAO.updateBill(billUpdate)){
                            System.out.println("Cap nhat phieu xuat thanh cong!!!");
                            System.out.println(billUpdate.toString());
                        }
                    }
                    break;
                case 4:
                    List<Bill_Detail> bill_details = bill_detailDAO.getAllBillDetail();
                    for (Bill_Detail bill_detail: bill_details){
                        System.out.println(bill_detail.toString());
                    }
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

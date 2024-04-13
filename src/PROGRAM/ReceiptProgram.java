package PROGRAM;

import DAO.*;
import ENTITY.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ReceiptProgram {
    public void runReceipt() {
        Scanner scanner = new Scanner(System.in);
        BillDAO billDAO = new BillDAOImpl();
        Bill_DetailDAO bill_detailDAO = new Bill_DetailDAOImpl();
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
            switch (choice) {
                case 1:
                    List<Bill> bills = billDAO.getReceiptList();
                    for (Bill bill : bills) {
                        System.out.println(bill.toString());
                    }
                    break;
                case 2:
                    Bill bill = new Bill();
                    System.out.println("Moi ban nhap thong tin phieu nhap");
                    bill.InputData(scanner);
                    if (billDAO.addReceiptBill(bill)) {
                        System.out.println("Them phieu nhap thanh cong!");
                    } else {
                        System.err.println("Loi! Vui long nhap lai!!!");
                    }
                    break;
                case 3:
                    System.out.println("Moi ban nhap ID phieu nhap can cap nhat");
                    int bill_Id = Integer.parseInt(scanner.nextLine());
                    Bill billUpdate = billDAO.findBillById(bill_Id);
                    if (billUpdate == null) {
                        System.err.println("Rat tiec, khong tim thay ID phieu nhap can cap nhat!");
                    } else if (!billUpdate.getBill_Type()) {
                        System.err.println("Rat tiec, ma ID ban vua nhap la phieu xuat");
                    } else if (billUpdate.getBill_Status() == 2) {
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
                        if (billDAO.updateBill(billUpdate)) {
                            System.out.println("Cap nhat phieu nhap thanh cong!!!");
                            System.out.println(billUpdate.toString());
                        }
                    }
                    break;
                case 4:
                    List<Bill_Detail> bill_details = bill_detailDAO.getAllReceiptDetail();
                    for (Bill_Detail bill_detail : bill_details) {
                        System.out.println(bill_detail.toString());
                    }
                    break;
                case 5:
                    System.out.println("Moi ban nhap vao id phieu nhap can duyet");
                    int bill_Id_Type_Update = Integer.parseInt(scanner.nextLine());
                    Bill billTypeUpdate = billDAO.findBillById(bill_Id_Type_Update);
                    if (billTypeUpdate == null) {
                        System.err.println("Rat tiec, khong tim thay ID phieu nhap can duyet!");
                    } else {
                        if (!billTypeUpdate.getBill_Type()) {
                            System.err.println("Rat tiec, ma ID ban vua nhap la phieu xuat");
                        } else {
                            if (billTypeUpdate.getBill_Status() == 2) {
                                System.out.println("Phieu nhap dang o trang thai duyet, khong can thao tac");
                                System.out.println(billTypeUpdate.toString());
                            } else if (billTypeUpdate.getBill_Status() == 0) {
                                System.out.println("Co phai ban muon duyet phieu nhap nay?");
                                System.out.println(billTypeUpdate.toString());
                                System.out.println("Moi ban nhap 2 neu muon duyet, hoac phim bat ky neu muon huy thao tac");
                                if (Integer.parseInt(scanner.nextLine()) == 2) {
                                    billTypeUpdate.setBill_Status(2);
                                    if (billDAO.updateBillStatus(billTypeUpdate)) {
                                        System.out.println("Duyet thanh cong!!!");
                                        System.out.println(billTypeUpdate.toString());
                                    }
                                } else {
                                    System.exit(0);

                                }
                            } else {
                                System.err.println("Loi! Phieu nhap nay dang o trang thai huy, khong thac tac duoc!");
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("Moi ban nhap vao tu khoa muon tim kiem");
                    String keyWord = scanner.nextLine();
                    List<Bill> billListSearchByName = billDAO.searchReceiptByCode(keyWord);
                    int countSearch = 0;
                    for (Bill bill1 : billListSearchByName) {
                        System.out.println(bill1.toString());
                        countSearch++;
                    }
                    if (countSearch == 0) {
                        System.err.println("Khong tim thay ket qua nao voi tu khoa: " + keyWord);
                    }
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

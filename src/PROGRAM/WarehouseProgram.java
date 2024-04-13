package PROGRAM;

import DAO.*;
import ENTITY.Account;
import ENTITY.Bill;

import java.util.List;
import java.util.Scanner;

public class WarehouseProgram {
    private static ProductProgram productProgram = new ProductProgram();
    private static AccountProgram accountProgram = new AccountProgram();
    private static ReceiptProgram receiptProgram = new ReceiptProgram();
    private static BillProgram billProgram = new BillProgram();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillDAO billDAO = new BillDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        do {
            System.out.println("===CHAO MUNG BAN DEN VOI KHOHANG.COM===");
            System.out.println("1. Log in");
            System.out.println("2. Exit");
            int choiceOne = Integer.parseInt(scanner.nextLine());
            switch (choiceOne) {
                case 1:
                    do {
                        System.out.println("Moi ban nhap username:");
                        String userName = scanner.nextLine();
                        System.out.println("Moi ban nhap vao password:");
                        String password = scanner.nextLine();
                        Account account = accountDAO.login(userName, password);
                        if (account != null) {
                            if (!account.getPermission()) {
                                do {
                                    System.out.println("Xin Chao Admin " + account.getUserName());
                                    System.out.println("===QUAN LY KHO HANG CHO ADMIN===");
                                    System.out.println("""
                                            1. Quản lý sản phẩm
                                            2. Quản lý tài khoản
                                            3. Quản lý phiếu nhập
                                            4. Quản lý phiếu xuất
                                            5. Trở lại
                                            6. Thoát
                                            """);
                                    int choice = Integer.parseInt(scanner.nextLine());
                                    switch (choice) {
                                        case 1:
                                            productProgram.runProduct();
                                            break;
                                        case 2:
                                            accountProgram.runAccount();
                                            break;
                                        case 3:
                                            receiptProgram.runReceipt();
                                            break;
                                        case 4:
                                            billProgram.runBill();
                                            break;
                                        case 5:
                                            main(new String[]{});
                                            break;
                                        case 6:
                                            System.exit(0);
                                        default:
                                            System.out.println("Loi!!! Vui long nhap lai!!!");
                                            break;
                                    }
                                } while (true);
                            } else {
                                do {
                                    System.out.println("Xin Chao User " + account.getUserName());
                                    System.out.println("===QUAN LY KHO HANG CHO USER===");
                                    System.out.println("""
                                            1. Danh sách phiếu nhập theo trạng thái
                                            2. Tạo phiếu nhập
                                            3. Cập nhật phiếu nhập
                                            4. Tìm kiếm phiếu nhập
                                            5. Danh sách phiếu xuất theo trạng thái
                                            6. Tạo phiếu xuất
                                            7. Cập nhật phiếu xuất
                                            8. Tìm kiếm phiếu xuất
                                            9. Thoát
                                            """);
                                    int choice = Integer.parseInt(scanner.nextLine());
                                    switch (choice) {
                                        case 1:
                                            List<Bill> userReceiptList = billDAO.getReceiptListForUser(account.getAcc_Id());
                                            for (Bill bill : userReceiptList) {
                                                System.out.println(bill.toString());
                                            }
                                            break;
                                        case 2:
                                            Bill userReceipt = new Bill();
                                            System.out.println("Moi ban nhap thong tin phieu nhap");
                                            userReceipt.InputData(scanner);
                                            if (billDAO.addReceipt(userReceipt)) {
                                                System.out.println("Them phieu nhap thanh cong!");
                                            } else {
                                                System.err.println("Loi! Vui long nhap lai!!!");
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Moi ban nhap ID phieu nhap can cap nhat");
                                            int bill_Id = Integer.parseInt(scanner.nextLine());
                                            Bill receiptUpdateForUser = billDAO.findBillById(bill_Id);
                                            if (receiptUpdateForUser == null) {
                                                System.err.println("Rat tiec, khong tim thay ID phieu nhap can cap nhat!");
                                            } else if (account.getAcc_Id() != receiptUpdateForUser.getAccount_Id_Created()) {
                                                System.err.println("Rat tiec, khong phai phieu nhap do ban tao!");
                                            } else if (!receiptUpdateForUser.getBill_Type()) {
                                                System.err.println("Rat tiec, ma ID ban vua nhap la phieu xuat");
                                            } else if (receiptUpdateForUser.getBill_Status() == 2) {
                                                System.err.println("Rat tiec, phieu nhap dang o trang thai duyet, khong duoc thao tac");
                                            } else {
                                                System.out.println("Co phai ban muon thay doi thong tin phieu nhap nay khong?");
                                                System.out.println(receiptUpdateForUser.toString());
                                                System.out.println("Xin moi nhap thong tin moi:");
                                                System.out.println("Nhap vao ma code phieu nhap:");
                                                receiptUpdateForUser.setBill_Type(Boolean.parseBoolean(scanner.nextLine()));
                                                System.out.println("Moi ban nhap trang thai phieu nhap: 0 - Tao, 1 - Huy, 2 - Duyet");
                                                receiptUpdateForUser.setBill_Status(Integer.parseInt(scanner.nextLine()));
                                                if (billDAO.updateReceiptForUser(receiptUpdateForUser)) {
                                                    System.out.println("Cap nhat phieu nhap thanh cong!!!");
                                                    System.out.println(receiptUpdateForUser.toString());
                                                }
                                            }
                                            break;
                                        case 4:
                                            System.out.println("Moi ban nhap vao tu khoa muon tim kiem");
                                            String keyWord = scanner.nextLine();
                                            List<Bill> billListSearchByName = billDAO.searchReceiptByCodeForUser(account.getAcc_Id(),keyWord);
                                            int countSearch = 0;
                                            for (Bill bill1 : billListSearchByName) {
                                                System.out.println(bill1.toString());
                                                countSearch++;
                                            }
                                            if (countSearch == 0) {
                                                System.err.println("Khong tim thay ket qua nao voi tu khoa: " + keyWord);
                                            }
                                            break;
                                        case 5:
                                            List<Bill> userBillList = billDAO.getBillListForUser(account.getAcc_Id());
                                            for (Bill bill : userBillList) {
                                                System.out.println(bill.toString());
                                            }
                                            break;
                                        case 6:
                                            Bill userBill = new Bill();
                                            System.out.println("Moi ban nhap thong tin phieu nhap");
                                            userBill.InputData(scanner);
                                            if (billDAO.addBill(userBill)) {
                                                System.out.println("Them phieu nhap thanh cong!");
                                            } else {
                                                System.err.println("Loi! Vui long nhap lai!!!");
                                            }
                                            break;
                                        case 7:
                                            System.out.println("Moi ban nhap ID phieu xuat can cap nhat");
                                            int bill_Id2 = Integer.parseInt(scanner.nextLine());
                                            Bill billUpdateForUser = billDAO.findBillById(bill_Id2);
                                            if (billUpdateForUser == null) {
                                                System.err.println("Rat tiec, khong tim thay ID phieu xuat can cap nhat!");
                                            } else if (account.getAcc_Id() != billUpdateForUser.getAccount_Id_Created()) {
                                                System.err.println("Rat tiec, khong phai phieu xuat do ban tao!");
                                            } else if (!billUpdateForUser.getBill_Type()) {
                                                System.err.println("Rat tiec, ma ID ban vua nhap la phieu nhap");
                                            } else if (billUpdateForUser.getBill_Status() == 2) {
                                                System.err.println("Rat tiec, phieu xuat dang o trang thai duyet, khong duoc thao tac");
                                            } else {
                                                System.out.println("Co phai ban muon thay doi thong tin phieu xuat nay khong?");
                                                System.out.println(billUpdateForUser.toString());
                                                System.out.println("Xin moi nhap thong tin moi:");
                                                System.out.println("Nhap vao ma code phieu xuat:");
                                                billUpdateForUser.setBill_Type(Boolean.parseBoolean(scanner.nextLine()));
                                                System.out.println("Moi ban nhap trang thai phieu nhap: 0 - Tao, 1 - Huy, 2 - Duyet");
                                                billUpdateForUser.setBill_Status(Integer.parseInt(scanner.nextLine()));
                                                if (billDAO.updateBillForUser(billUpdateForUser)) {
                                                    System.out.println("Cap nhat phieu xuat thanh cong!!!");
                                                    System.out.println(billUpdateForUser.toString());
                                                }
                                            }
                                            break;
                                        case 8:
                                            System.out.println("Moi ban nhap vao tu khoa muon tim kiem");
                                            String keyWord2 = scanner.nextLine();
                                            List<Bill> billListSearchByName2 = billDAO.searchReceiptByCodeForUser(account.getAcc_Id(),keyWord2);
                                            int countSearch2 = 0;
                                            for (Bill bill1 : billListSearchByName2) {
                                                System.out.println(bill1.toString());
                                                countSearch2++;
                                            }
                                            if (countSearch2 == 0) {
                                                System.err.println("Khong tim thay ket qua nao voi tu khoa: " + keyWord2);
                                            }
                                            break;
                                        case 9:
                                            System.exit(0);
                                        default:
                                            System.err.println("Loi! Vui long nhap lai!");
                                            break;
                                    }
                                } while (true);
                            }
                        } else {
                            System.err.println("Sai thong tin tai khoan!");
                        }
                    } while (true);
                case 2:
                    System.exit(0);
                default:
                    System.err.println("Loi! Vui long nhap lai!");
                    break;
            }
        } while (true);
    }
}
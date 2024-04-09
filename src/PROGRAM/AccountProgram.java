package PROGRAM;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import ENTITY.Account;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        do {
            System.out.println("===QUAN LY TAI KHOAN===");
            System.out.println("""
                    1. Danh sách tài khoản
                    2. Tạo tài khoản mới
                    3. Cập nhật trạng thái tài khoản
                    4. Thoát""");
            System.out.println("Moi ban nhap:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Account> accounts = accountDAO.getAllAccount();
                    for (Account account: accounts){
                        System.out.println(account.toString());
                    }
                    break;
                case 2:
                    Account account = new Account();
                    System.out.println("Moi ban nhap thong tin tai khoan can them:");
                    account.accountInputData(scanner);
                    if (accountDAO.addAccount(account)){
                        System.out.println("Them tai khoan thanh cong!!");
                        System.out.println(account.toString());
                    } else {
                        System.err.println("Loi, vui long nhap lai!!!");
                    }
                    break;
                case 3:
                    System.out.println("Moi ban nhap vao id tai khoan can sua trang thai:");
                    int acc_Id = Integer.parseInt(scanner.nextLine());
                    Account updateAccountStatus = accountDAO.findAccountById(acc_Id);
                    if (updateAccountStatus == null){
                        System.err.println("Rat tiec khong tim thay ma tai khoan can cap nhat!");
                    } else {
                        System.out.println("Moi ban nhap trang thai muon sua:");
                        updateAccountStatus.setAccount_Status(Boolean.parseBoolean(scanner.nextLine()));
                        if (accountDAO.updateAccountStatus(updateAccountStatus)){
                            System.out.println("Cap nhat trang thai tai khoan thanh cong!!!");
                            System.out.println(updateAccountStatus.toString());
                        }
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhap sai, vui long nhap lai!");
            }
        } while (true);
    }
}

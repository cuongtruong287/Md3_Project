package PROGRAM;

import DAO.*;
import ENTITY.Account;

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
                            do {
                                System.out.println("===QUAN LY KHO HANG ===");
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
                                        if (account.getPermission()) {
                                            System.out.println("User khong co quyen truy cap vao danh muc san pham!!");
                                        } else {
                                            productProgram.runProduct();
                                        }
                                        break;
                                    case 2:
                                        if (account.getPermission()) {
                                            System.out.println("User khong co quyen truy cap vao danh muc tai khoan!!");
                                        } else {
                                            accountProgram.runAccount();
                                        }
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
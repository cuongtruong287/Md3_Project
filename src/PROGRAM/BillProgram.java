package PROGRAM;

import DAO.*;

import java.util.Scanner;

public class BillProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        BillDAO billDAO = new BillDAOImpl();
        do {
            System.out.println("===QUAN LY HOA DON===");
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
                    break;
                case 2:
                    break;
                case 3:
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

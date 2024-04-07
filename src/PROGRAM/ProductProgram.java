package PROGRAM;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import ENTITY.Product;

import java.util.List;
import java.util.Scanner;

public class ProductProgram {
    public static void main(String[] args) {
        // khoi tao doi tuong trong lop ProductDaoImpl
        ProductDAO productDAO = new ProductDAOImpl();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("===QUAN LY SAN PHAM===");
            System.out.println("""
                    1. Danh sách sản phẩm
                    2. Thêm mới sản phẩm
                    3. Cập nhật sản phẩm
                    4. Tìm kiếm sản phẩm
                    5. Cập nhật trạng thái sản phẩm 
                    6. Thoát
                    """);
            System.out.println("Moi ban nhap:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    List<Product> products = productDAO.getAllProduct();
                    for (Product product : products) {
                        System.out.println(product.toString());
                    }
                    break;
                case 2:
                    Product product = new Product();
                    System.out.println("MOi ban nhap thong tin san pham:");
                    product.productInputData(scanner);
                    if (productDAO.addProduct(product)) {
                        System.out.println("Them san pham moi thanh cong!");
                    } else {
                        System.err.println("Loi, vui long nhap lai!");
                    }
                    break;
                case 3:
                    System.out.println("Moi ban nhap vao id can sua");
                    String product_Id = scanner.nextLine();
                    Product productUpdate = productDAO.findById(product_Id);
                    if (productUpdate == null){
                        System.err.println("Rat tiec khong tim thay ma san pham can sua!");
                    } else {
                        System.out.println("Co phai ban muon thay doi thong tin san pham nay khong?");
                        System.out.println(productUpdate.toString());
                        System.out.println("Xin moi nhap thong tin moi:");
                        System.out.println("Nhap vao ten san pham:");
                        productUpdate.setProduct_Name(scanner.nextLine());
                        System.out.println("Nhap vao ten nha san xuat:");
                        productUpdate.setManufacturer(scanner.nextLine());
                        System.out.println("Nhap vao lo chua san pham:");
                        productUpdate.setBatch(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Moi ban nhap so luong hang:");
                        productUpdate.setProduct_Quantity(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Moi ban nhap trang thai san pham:");
                        productUpdate.setProduct_Status(Boolean.parseBoolean(scanner.nextLine()));
                        if (productDAO.updateProduct(productUpdate)){
                            System.out.println("Cap nhat san pham thanh cong!!!");
                            System.out.println(productUpdate.toString());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Moi ban nhap vao tu khoa muon tim kiem");
                    String keyWord = scanner.nextLine();
                    List<Product> productListSearchByName = productDAO.searchProductByName(keyWord);
                    int countSearch = 0;
                    for (Product product1 : productListSearchByName){
                        System.out.println(product1.toString());
                        countSearch++;
                    }
                    if (countSearch == 0){
                        System.err.println("Khong tim thay ket qua nao voi tu khoa: " + keyWord);
                    }
                    break;
                case 5:
                    System.out.println("Moi ban nhap vao id can sua trang thai");
                    String product_Id2 = scanner.nextLine();
                    Product productStatusUpdate = productDAO.findById(product_Id2);
                    if (productStatusUpdate == null){
                        System.err.println("Rat tiec khong tim thay ma san pham can sua!");
                    } else {
                        System.out.println("Moi ban nhap trang thai san pham:");
                        productStatusUpdate.setProduct_Status(Boolean.parseBoolean(scanner.nextLine()));
                        if (productDAO.updateProduct(productStatusUpdate)){
                            System.out.println("Cap nhat san pham thanh cong!!!");
                            System.out.println(productStatusUpdate.toString());
                        }
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhap sai, moi chon lai");
            }
        } while (true);
    }
}

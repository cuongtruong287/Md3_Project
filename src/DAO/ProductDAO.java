package DAO;

import ENTITY.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProduct();

    boolean addProduct(Product product);

    Product findById(String product_Id);

    boolean updateProduct(Product product);

    List<Product> searchProductByName(String keyword);

    boolean updateProductStatus(Product product);
}

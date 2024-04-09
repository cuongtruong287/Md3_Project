package DAO;

import ENTITY.Product;
import UTIL.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProduct() {
        // mo ket noi
        Connection connection = ConnectionDB.openConnection();
        List<Product> products = new ArrayList<>();
        // thuc hien truy van cau lenh sql thong qua
        try {
            String sqlSelectAllProduct = "select * from PRODUCT";
            PreparedStatement statement = connection.prepareStatement(sqlSelectAllProduct);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_Id(resultSet.getString("product_Id"));
                product.setProduct_Name(resultSet.getString("product_Name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("product_Created"));
                product.setBatch(resultSet.getInt("product_Batch"));
                product.setProduct_Quantity(resultSet.getInt("product_Quantity"));
                product.setProduct_Status(resultSet.getBoolean("product_Status"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sqlAddProduct = "insert into PRODUCT(product_Id, product_Name, manufacturer, product_Batch, product_Quantity) values (?, ?, ?, ?, ? )";
            PreparedStatement statement = connection.prepareStatement(sqlAddProduct);
            statement.setString(1, product.getProduct_Id());
            statement.setString(2, product.getProduct_Name());
            statement.setString(3, product.getManufacturer());
            statement.setInt(4, product.getBatch());
            statement.setInt(5, product.getProduct_Quantity());
            int check = statement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Product findById(String product_Id) {
        Connection connection = ConnectionDB.openConnection();
        Product product = new Product();
        try {
            String sqlFindProductById = "select * from PRODUCT where product_Id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlFindProductById);
            statement.setString(1,product_Id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            while (resultSet.next()) {
                product.setProduct_Id(resultSet.getString("product_Id"));
                product.setProduct_Name(resultSet.getString("product_Name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("product_Created"));
                product.setBatch(resultSet.getInt("product_Batch"));
                product.setProduct_Quantity(resultSet.getInt("product_Quantity"));
                product.setProduct_Status(resultSet.getBoolean("product_Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection connection = ConnectionDB.openConnection();
        String slqUpdateProduct = "update PRODUCT set product_Name = ?, manufacturer = ?, product_Batch = ?, product_Quantity = ?, product_Status = ? where product_Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(slqUpdateProduct);
            statement.setString(1, product.getProduct_Name());
            statement.setString(2, product.getManufacturer());
            statement.setInt(3, product.getBatch());
            statement.setInt(4, product.getProduct_Quantity());
            statement.setBoolean(5, product.getProduct_Status());
            statement.setString(6, product.getProduct_Id());
            int check = statement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public List<Product> searchProductByName(String keyword) {
        Connection connection = ConnectionDB.openConnection();
        List<Product> products = new ArrayList<>();
        try {
            String sqlProductSearchByName = "select * from PRODUCT where product_Name like ? limit 10";
            PreparedStatement statement = connection.prepareStatement(sqlProductSearchByName);
            statement.setString(1,"%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_Id(resultSet.getString("product_Id"));
                product.setProduct_Name(resultSet.getString("product_Name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("product_Created"));
                product.setBatch(resultSet.getInt("product_batch"));
                product.setProduct_Quantity(resultSet.getInt("product_Quantity"));
                product.setProduct_Status(resultSet.getBoolean("product_Status"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return products;
    }

    @Override
    public boolean updateProductStatus(Product product) {
        Connection connection = ConnectionDB.openConnection();
        String sqlUpdateProductStatus = "update PRODUCT set product_Status = ? where product_Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlUpdateProductStatus);
            statement.setBoolean(1, product.getProduct_Status());
            statement.setString(2, product.getProduct_Id());
            int check = statement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }
}

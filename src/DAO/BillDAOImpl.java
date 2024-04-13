package DAO;

import ENTITY.Bill;
import UTIL.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO{

    @Override
    public List<Bill> getReceiptList() {
        Connection connection = ConnectionDB.openConnection();
        List<Bill> bills = new ArrayList<>();
        String sqlSelectReceipt = "select * from BILL where bill_Type = 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlSelectReceipt);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill bill = new Bill();
                bill.setBill_Id(resultSet.getInt("bill_id"));
                bill.setBill_Code(resultSet.getString("bill_Code"));
                bill.setBill_Type(resultSet.getBoolean("bill_Type"));
                bill.setAccount_Id_Created(resultSet.getInt("account_Id_Created"));
                bill.setBill_Created(resultSet.getDate("bill_Created"));
                bill.setBill_Auth_Date(resultSet.getDate("bill_Auth_Date"));
                bill.setBill_Status(resultSet.getInt("bill_Status"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return bills;
    }

    @Override
    public List<Bill> getBillList() {
        Connection connection = ConnectionDB.openConnection();
        List<Bill> bills = new ArrayList<>();
        String sqlSelectAllBill = "select * from BILL where bill_Type = 0";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlSelectAllBill);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill bill = new Bill();
                bill.setBill_Id(resultSet.getInt("bill_id"));
                bill.setBill_Code(resultSet.getString("bill_Code"));
                bill.setBill_Type(resultSet.getBoolean("bill_Type"));
                bill.setAccount_Id_Created(resultSet.getInt("account_Id_Created"));
                bill.setBill_Created(resultSet.getDate("bill_Created"));
                bill.setBill_Auth_Date(resultSet.getDate("bill_Auth_Date"));
                bill.setBill_Status(resultSet.getInt("bill_Status"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return bills;
    }

    @Override
    public boolean addReceipt(Bill bill) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sqlAddReceipt = "insert into BILL(bill_Code, bill_Type, account_Id_Created) value (?, 1, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlAddReceipt);
            statement.setString(1, bill.getBill_Code());
            statement.setInt(2, bill.getAccount_Id_Created());
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
    public boolean addBill(Bill bill) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sqlAddBill = "insert into BILL(bill_Code, bill_Type, account_Id_Created) value (?, 0, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlAddBill);
            statement.setString(1, bill.getBill_Code());
            statement.setInt(2, bill.getAccount_Id_Created());
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
    public Bill findBillById(int bill_id) {
        Connection connection = ConnectionDB.openConnection();
        Bill bill = new Bill();
        try {
            String sqlFindBillById = "select * from BILL where bill_id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlFindBillById);
            statement.setInt(1,bill_id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            while (resultSet.next()) {
                bill.setBill_Id(resultSet.getInt("bill_id"));
                bill.setBill_Code(resultSet.getString("bill_Code"));
                bill.setBill_Type(resultSet.getBoolean("bill_Type"));
                bill.setAccount_Id_Created(resultSet.getInt("account_Id_Created"));
                bill.setBill_Created(resultSet.getDate("bill_Created"));
                bill.setBill_Auth_Date(resultSet.getDate("bill_Auth_Date"));
                bill.setBill_Status(resultSet.getInt("bill_Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return bill;
    }

    @Override
    public boolean updateBill(Bill bill) {
        Connection connection = ConnectionDB.openConnection();
        String slqUpdateBill = "update Bill set bill_Code = ?, bill_Type = ?, account_Id_Created = ?, bill_Status = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(slqUpdateBill);
            statement.setString(1, bill.getBill_Code());
            statement.setBoolean(2, bill.getBill_Type());
            statement.setInt(3, bill.getAccount_Id_Created());
            statement.setInt(4, bill.getBill_Status());
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
    public boolean updateBillStatus(Bill bill) {
        Connection connection = ConnectionDB.openConnection();
        String slqUpdateBill = "update Bill set bill_Status = ? where bill_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(slqUpdateBill);
            statement.setInt(1, bill.getBill_Status());
            statement.setInt(2, bill.getBill_Id());
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
    public List<Bill> searchBillByCode(String keyword) {
        Connection connection = ConnectionDB.openConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            String sqlProductSearchByName = "select * from BILL where bill_Type = 0 and bill_Code like ?";
            PreparedStatement statement = connection.prepareStatement(sqlProductSearchByName);
            statement.setString(1,"%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBill_Id(resultSet.getInt("bill_id"));
                bill.setBill_Code(resultSet.getString("bill_Code"));
                bill.setBill_Type(resultSet.getBoolean("bill_Type"));
                bill.setAccount_Id_Created(resultSet.getInt("account_Id_Created"));
                bill.setBill_Created(resultSet.getDate("bill_Created"));
                bill.setBill_Auth_Date(resultSet.getDate("bill_Auth_Date"));
                bill.setBill_Status(resultSet.getInt("bill_Status"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return bills;
    }

    @Override
    public List<Bill> searchReceiptByCode(String keyword) {
        Connection connection = ConnectionDB.openConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            String sqlProductSearchByName = "select * from BILL where bill_Type = 1 and bill_Code like ?";
            PreparedStatement statement = connection.prepareStatement(sqlProductSearchByName);
            statement.setString(1,"%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBill_Id(resultSet.getInt("bill_id"));
                bill.setBill_Code(resultSet.getString("bill_Code"));
                bill.setBill_Type(resultSet.getBoolean("bill_Type"));
                bill.setAccount_Id_Created(resultSet.getInt("account_Id_Created"));
                bill.setBill_Created(resultSet.getDate("bill_Created"));
                bill.setBill_Auth_Date(resultSet.getDate("bill_Auth_Date"));
                bill.setBill_Status(resultSet.getInt("bill_Status"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return bills;
    }
}

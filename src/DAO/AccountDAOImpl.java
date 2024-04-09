package DAO;

import ENTITY.Account;
import ENTITY.Product;
import UTIL.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{
    @Override
    public Account login(String userName, String password) {
        Connection connection = ConnectionDB.openConnection();
        Account account = new Account();
        try {
            String sql = "select * from account where user_Name = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()){
                return null;
            }
            while (resultSet.next()){
                account.setAcc_Id(resultSet.getInt("acc_Id"));
                account.setUserName(resultSet.getString("user_Name"));
                account.setPermission(resultSet.getBoolean("permission"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return account;
    }

    @Override
    public List<Account> getAllAccount() {
        Connection connection = ConnectionDB.openConnection();
        List<Account> accounts = new ArrayList<>();
        String slqSelectAllAccount = "select * from ACCOUNT";
        try {
            PreparedStatement statement = connection.prepareStatement(slqSelectAllAccount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account();
                account.setAcc_Id(resultSet.getInt("acc_Id"));
                account.setUserName(resultSet.getString("user_Name"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setAccount_Status(resultSet.getBoolean("acc_status"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return accounts;
    }

    @Override
    public boolean addAccount(Account account) {
        Connection connection = ConnectionDB.openConnection();
        String sqlAddAccount = "insert into ACCOUNT(user_Name, password, permission) value (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlAddAccount);
            statement.setString(1,account.getUserName());
            statement.setString(2,account.getPassword());
            statement.setBoolean(3,account.getPermission());
            int check = statement.executeUpdate();
            if (check > 0){
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
    public Account findAccountById(int acc_Id) {
        Connection connection = ConnectionDB.openConnection();
        Account account = new Account();
        String sqlFindAccountById = "select * from ACCOUNT where acc_Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlFindAccountById);
            statement.setInt(1,acc_Id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()){
                return null;
            }
            while (resultSet.next()){
                account.setAcc_Id(resultSet.getInt("acc_Id"));
                account.setUserName(resultSet.getString("user_Name"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setAccount_Status(resultSet.getBoolean("acc_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return account;
    }

    @Override
    public boolean updateAccountStatus(Account account) {
        Connection connection = ConnectionDB.openConnection();
        String sqlUpdateAccountStatus = "update ACCOUNT set acc_status = ? where acc_Id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlUpdateAccountStatus);
            statement.setBoolean(1,account.getAccount_Status());
            statement.setInt(2,account.getAcc_Id());
            int check = statement.executeUpdate();
            if (check > 0){
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

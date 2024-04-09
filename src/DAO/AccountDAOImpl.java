package DAO;

import ENTITY.Account;
import ENTITY.Product;
import UTIL.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

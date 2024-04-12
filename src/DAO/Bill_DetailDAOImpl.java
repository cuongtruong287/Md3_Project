package DAO;

import ENTITY.Bill;
import ENTITY.Bill_Detail;
import UTIL.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Bill_DetailDAOImpl implements Bill_DetailDAO{
    @Override
    public List<Bill_Detail> getAllBillDetail() {
        Connection connection = ConnectionDB.openConnection();
        List<Bill_Detail> billDetails = new ArrayList<>();
        String sqlSelectBillDetail = """
                select *
                from BILL_DETAIL
                inner join BIlL
                on BILL_DETAIL.bill_Id = BILL.bill_id
                where bill_Type = 0;;""";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlSelectBillDetail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill_Detail billDetail = new Bill_Detail();
                billDetail.setBill_Detail_Id(resultSet.getInt("Bill_Detail_Id"));
                billDetail.setBill_Id(resultSet.getInt("bill_Id"));
                billDetail.setProduct_Id(resultSet.getString("product_Id"));
                billDetail.setQuantity_Bill(resultSet.getInt("quantity_Bill"));
                billDetail.setPrice(resultSet.getInt("price"));
                billDetails.add(billDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return billDetails;
    }

    @Override
    public List<Bill_Detail> getAllReceiptDetail() {
        Connection connection = ConnectionDB.openConnection();
        List<Bill_Detail> billDetails = new ArrayList<>();
        String sqlSelectBillDetail = """
                select *
                from BILL_DETAIL
                inner join BIlL
                on BILL_DETAIL.bill_Id = BILL.bill_id
                where bill_Type = 1;;""";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlSelectBillDetail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bill_Detail billDetail = new Bill_Detail();
                billDetail.setBill_Detail_Id(resultSet.getInt("Bill_Detail_Id"));
                billDetail.setBill_Id(resultSet.getInt("bill_Id"));
                billDetail.setProduct_Id(resultSet.getString("product_Id"));
                billDetail.setQuantity_Bill(resultSet.getInt("quantity_Bill"));
                billDetail.setPrice(resultSet.getInt("price"));
                billDetails.add(billDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return billDetails;
    }
}

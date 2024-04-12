package DAO;

import ENTITY.Bill_Detail;

import java.util.List;

public interface Bill_DetailDAO {
    List<Bill_Detail> getAllBillDetail();
    List<Bill_Detail> getAllReceiptDetail();

}

package DAO;

import ENTITY.Bill;

import java.util.List;

public interface BillDAO {
    List<Bill> getReceiptList();
    List<Bill> getBillList();
    boolean addReceipt(Bill bill);
    boolean addBill(Bill bill);
    Bill findBillById(int bill_id);
    boolean updateBill(Bill bill);
    boolean updateBillStatus(Bill bill);
    List<Bill> searchBillByCode(String keyword);
    List<Bill> searchReceiptByCode(String keyword);


}

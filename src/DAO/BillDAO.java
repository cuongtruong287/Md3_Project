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
    List<Bill> getReceiptListForUser(int acc_Id);
    List<Bill> getBillListForUser(int acc_Id);
    boolean updateBillForUser(Bill bill);
    boolean updateReceiptForUser(Bill bill);
    List<Bill> searchBillByCodeForUser(int acc_Id, String keyword);
    List<Bill> searchReceiptByCodeForUser(int acc_Id, String keyword);





}

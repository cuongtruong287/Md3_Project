package DAO;

import ENTITY.Bill;

import java.util.List;

public interface BillDAO {
    List<Bill> getReceiptList();
    List<Bill> getBillList();
    boolean addReceiptBill(Bill bill);
    boolean addBill(Bill bill);
    Bill findBillById(int bill_id);
    boolean updateBill(Bill bill);
    boolean updateBillStatus(Bill bill);


}

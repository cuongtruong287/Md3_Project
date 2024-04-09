package DAO;

import ENTITY.Account;

import java.util.List;

public interface AccountDAO {
    Account login(String username, String password);
    List<Account> geeAllAccount();
    boolean addAccount(Account account);
    Account findAccountById(int acc_Id);
    boolean updateAccountStatus(Account account);
}
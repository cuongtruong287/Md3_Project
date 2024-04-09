package DAO;

import ENTITY.Account;

public interface AccountDAO {
    Account login(String username, String password);
}

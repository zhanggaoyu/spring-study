package cn.ann.dao;

import cn.ann.beans.Account;

import java.sql.SQLException;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 9:21
 */
public interface AccountDao {
    void updateAccount(Account account) throws SQLException;

    Account getAccountById(Integer id) throws SQLException;

    Account getAccountByName(String name) throws SQLException;

}

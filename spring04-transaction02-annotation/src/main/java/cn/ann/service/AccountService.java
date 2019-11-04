package cn.ann.service;

import cn.ann.beans.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 9:22
 */
public interface AccountService {
    void transfer(String source, String target, Double money);

    Account getAccountById(Integer id) throws SQLException;

    Account getAccountByName(String name) throws SQLException;
}

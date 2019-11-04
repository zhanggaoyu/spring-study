package cn.ann.dao.impl;

import cn.ann.beans.Account;
import cn.ann.dao.AccountDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 9:21
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Resource(name = "runner")
    private QueryRunner runner;

    @Override
    public void updateAccount(Account account) throws SQLException {
        String sql = "update t_account set account_name = ?, account_money = ? where id = ?";
        runner.update(getConnection(), sql, account.getAccountName(), account.getAccountMoney(), account.getId());
    }

    @Override
    public Account getAccountById(Integer id) throws SQLException {
        String sql = "select * from t_account where id = ?";
        return runner.query(getConnection(), sql,
                new BeanHandler<>(Account.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
    }

    @Override
    public Account getAccountByName(String name) throws SQLException {
        String sql = "select * from t_account where account_name like ?";
        List<Account> accounts = runner.query(getConnection(), sql,
                new BeanListHandler<>(Account.class, new BasicRowProcessor(new GenerousBeanProcessor())), name);
        if (accounts == null || accounts.size() == 0) {
            throw new RuntimeException("查询结果空");
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("查询结果不唯一");
        }
        return accounts.get(0);
    }
}

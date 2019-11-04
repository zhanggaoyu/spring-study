package cn.ann.service.impl;

import cn.ann.beans.Account;
import cn.ann.dao.AccountDao;
import cn.ann.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 11:02
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource(name = "txTemplate")
    private TransactionTemplate txTemplate;
    @Resource(name = "accountDao")
    private AccountDao dao;

    @Override
    public void transfer(String source, String target, Double money) {
        txTemplate.execute(status -> {
            try {
                Account sourceAccount = dao.getAccountByName(source);
                Account targetAccount = dao.getAccountByName(target);
                sourceAccount.setAccountMoney(sourceAccount.getAccountMoney() - money);
                dao.updateAccount(sourceAccount);
//                int i = 1 / 0;
                targetAccount.setAccountMoney(targetAccount.getAccountMoney() + money);
                dao.updateAccount(targetAccount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Account getAccountById(Integer id) throws SQLException {
        return txTemplate.execute(status -> {
            try {
                return dao.getAccountById(id);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Account getAccountByName(String name) throws SQLException {
        return txTemplate.execute(status -> {
            try {
                return dao.getAccountByName(name);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}

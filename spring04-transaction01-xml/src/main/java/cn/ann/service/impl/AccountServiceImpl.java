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
    @Resource(name = "accountDao")
    private AccountDao dao;

    /**
     * 转账. 要注意的是, 转账时需要执行SQL的是同一个connection对象
     * @param source 转账人
     * @param target 转账目标
     * @param money 转账金额
     */
    @Override
    public void transfer(String source, String target, Double money) {
        try {
            Account sourceAccount = dao.getAccountByName(source);
            Account targetAccount = dao.getAccountByName(target);
            sourceAccount.setAccountMoney(sourceAccount.getAccountMoney() - money);
            dao.updateAccount(sourceAccount);
//            int i = 1 / 0;
            targetAccount.setAccountMoney(targetAccount.getAccountMoney() + money);
            dao.updateAccount(targetAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccountById(Integer id) throws SQLException {
        return dao.getAccountById(id);
    }

    @Override
    public Account getAccountByName(String name) throws SQLException {
        return dao.getAccountByName(name);
    }
}

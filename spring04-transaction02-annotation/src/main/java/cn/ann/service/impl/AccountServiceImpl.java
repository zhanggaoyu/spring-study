package cn.ann.service.impl;

import cn.ann.beans.Account;
import cn.ann.dao.AccountDao;
import cn.ann.service.AccountService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Pointcut("execution(* cn.ann.service..*.*(..))")
    public void txPc(){}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Account getAccountByName(String name) throws SQLException {
        return dao.getAccountByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Account getAccountById(Integer id) throws SQLException {
        return dao.getAccountById(id);
    }
}

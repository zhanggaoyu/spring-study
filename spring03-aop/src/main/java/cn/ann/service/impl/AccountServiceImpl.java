package cn.ann.service.impl;

import cn.ann.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-2 14:39
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Override
    public void saveAccount() {
        System.out.println("保存账户...");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("更新账户..." + i);
    }

    @Override
    public int delAccount() {
        System.out.println("删除账户...");
        return 0;
    }
}

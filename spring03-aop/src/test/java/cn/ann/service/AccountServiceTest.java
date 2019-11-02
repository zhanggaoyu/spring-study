package cn.ann.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-2 14:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountServiceTest {
    @Resource(name = "accountService")
    private AccountService service;

    @Test
    public void demo() {
        service.saveAccount();
    }

}

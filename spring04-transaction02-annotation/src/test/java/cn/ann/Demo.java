package cn.ann;

import cn.ann.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 11:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class Demo {
    @Resource(name = "accountService")
    private AccountService service;

    @Test
    public void demo01() {
        service.transfer("112233", "445566", 100d);
    }

}

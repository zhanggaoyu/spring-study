package cn.ann;

import cn.ann.beans.User;
import cn.ann.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 17:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Demo {
    @Resource(name = "userService")
    private UserService service;

    @Test
    public void demo01() throws SQLException {
        System.out.println(service.getUserById(1));
    }

    @Test
    public void demo02() throws SQLException {
        List<User> users = service.getUsers();
        users.forEach(System.out::println);
    }

}

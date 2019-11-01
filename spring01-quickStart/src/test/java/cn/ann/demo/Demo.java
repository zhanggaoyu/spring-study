package cn.ann.demo;

import cn.ann.CollectionDemo;
import cn.ann.Hello;
import cn.ann.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 15:28
 */
public class Demo {
    @Test
    public void demo01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Hello hello = (Hello) ac.getBean("hello");
        hello.hello();
    }

    /**
     * 测试创建bean的方式
     */
    @Test
    public void demo02() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
    }

    /**
     * 测试bean的作用范围
     */
    @Test
    public void demo03() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) ac.getBean("user");
        User user2 = (User) ac.getBean("user");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试bean的生命周期
     */
    @Test
    public void demo04() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
        ac.close();
    }

    /**
     * 测试复杂类型注入
     */
    @Test
    public void demo05() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionDemo demo = (CollectionDemo) ac.getBean("demo");
        demo.print();
    }


}

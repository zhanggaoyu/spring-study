package cn.ann;

import cn.ann.service.BeanService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 13:03
 */
public class Demo {
    @Test
    public void demo01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        for (int i = 0; i < 5; i++) {
            BeanService service = (BeanService) ac.getBean("beanService");
            service.add();
        }
    }
}

package test;

import cn.ann.service.BeanService;
import cn.ann.service.impl.BeanServiceImpl;
import org.junit.Test;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 20:57
 */
public class Demo {
    @Test
    public void demo01() {
        BeanService service = new BeanServiceImpl();
        service.add();
    }

    @Test
    public void demo02() {
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
        new BeanServiceImpl().add();
    }
}

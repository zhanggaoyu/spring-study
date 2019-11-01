package cn.ann.service.impl;

import cn.ann.dao.BeanDao;
import cn.ann.dao.impl.BeanDaoImpl;
import cn.ann.service.BeanService;
import cn.ann.util.BeanFactory;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 20:59
 */
public class BeanServiceImpl implements BeanService {
//    BeanDao dao = new BeanDaoImpl();
//    BeanDao dao = BeanFactory.getInstance("cn.ann.dao.impl.BeanDaoImpl");
    BeanDao dao = (BeanDao) BeanFactory.getInstance("beanDao");

    @Override
    public void add() {
        System.out.println(dao);
        dao.add();
    }
}

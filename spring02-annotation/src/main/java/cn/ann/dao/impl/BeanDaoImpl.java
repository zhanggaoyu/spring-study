package cn.ann.dao.impl;

import cn.ann.dao.BeanDao;
import org.springframework.stereotype.Repository;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 20:58
 */
@Repository("beanDao")
public class BeanDaoImpl implements BeanDao {
    @Override
    public void add() {
        System.out.println("添加bean信息");
    }
}

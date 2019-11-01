package cn.ann.service.impl;

import cn.ann.dao.BeanDao;
import cn.ann.service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-10-30 20:59
 */
@Service("beanService")
public class BeanServiceImpl implements BeanService {
//    @Autowired
//    @Qualifier("beanDao")
    @Resource(name = "beanDao")
    private BeanDao dao;
    @Value("")
    private String string;

    @Override
    public void add() {
        System.out.println(dao);
    }
}

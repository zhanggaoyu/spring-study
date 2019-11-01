package cn.ann.service.impl;

import cn.ann.beans.User;
import cn.ann.dao.UserDao;
import cn.ann.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 16:56
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao dao;

    @Override
    public User getUserById(Integer id) throws SQLException {
        return dao.getUserById(id);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return dao.getUsers();
    }
}

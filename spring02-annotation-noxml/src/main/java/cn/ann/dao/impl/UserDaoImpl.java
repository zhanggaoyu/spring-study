package cn.ann.dao.impl;

import cn.ann.beans.User;
import cn.ann.dao.UserDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 16:49
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource(name = "runner")
    private QueryRunner runner;

    @Override
    public User getUserById(Integer id) throws SQLException {
        String sql = "select * from t_user where id = ?";
        User user = runner.query(sql, new BeanHandler<>(User.class), id);
        return user;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        String sql = "select * from t_user";
        List<User> users = runner.query(sql, new BeanListHandler<>(User.class));
        return users;
    }
}

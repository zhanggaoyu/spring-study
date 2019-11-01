package cn.ann.dao;

import cn.ann.beans.User;

import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 16:45
 */
public interface UserDao {
    User getUserById(Integer id) throws SQLException;

    List<User> getUsers() throws SQLException;
}

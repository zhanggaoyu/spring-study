package cn.ann.service;

import cn.ann.beans.User;

import java.sql.SQLException;
import java.util.List;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 16:47
 */
public interface UserService {
    User getUserById(Integer id) throws SQLException;

    List<User> getUsers() throws SQLException;
}

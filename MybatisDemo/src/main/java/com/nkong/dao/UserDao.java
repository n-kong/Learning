package com.nkong.dao;

import com.nkong.bean.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> getUserLike(String value);

    List<User> getUserList();

    User getUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

    int updateUser2(Map<String, Object> map);
}

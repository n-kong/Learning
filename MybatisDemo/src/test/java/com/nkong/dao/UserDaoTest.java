package com.nkong.dao;

import com.nkong.bean.User;
import com.nkong.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {


    @Test
    public void testLike() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserLike("ha");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();

    }

    @Test
    public void test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();

    }

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testAddUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.addUser(new User(4, "jaja", "22"));
        if (res > 0) {
            System.out.println("插入成功！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.updateUser(new User(4, "wawa", "30"));
        if (res > 0) {
            System.out.println("更新成功！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.deleteUser(4);
        if (res > 0) {
            System.out.println("删除成功！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<String, Object> map = new HashMap<>();
        map.put("userid", 4);
        map.put("username", "uaua");
        int res = userDao.updateUser2(map);
        if (res > 0) {
            System.out.println("修改成功！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

}

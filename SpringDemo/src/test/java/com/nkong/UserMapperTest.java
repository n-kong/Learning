package com.nkong;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nkong.bean.User;
import com.nkong.mapper.UserMapper;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void test() {
        List<User> users = userMapper.selectList(null);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void add() {
        User user = new User();
        user.setName("nkong");
        user.setAge(20);
        user.setEmail("1152844372@qq.com");
        int insert = userMapper.insert(user);

        System.out.println(insert);

        System.out.println(user);
    }

    @Test
    void update() {
        User user = new User();
        user.setId(2);
        user.setName("nkong");
        user.setAge(24);
        user.setEmail("nkong1206@163.com");
        int i = userMapper.updateById(user);

        System.out.println(i);

        System.out.println(user);
    }

    @Test
    void page() {
        Page<User> page = new Page<>(1, 2);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);

        System.out.println(page.getTotal());
    }

}

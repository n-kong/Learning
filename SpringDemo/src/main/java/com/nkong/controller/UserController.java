package com.nkong.controller;

import com.nkong.bean.User;
import com.nkong.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/queryUser")
    public List<User> queryUserList() {
        List<User> users = userMapper.queryUserList();
        return users;
    }
}

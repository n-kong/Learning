package com.nkong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author nkong
 * @Date 2020/6/21 18:47
 * @Version 1.0
 **/

@RestController
public class HelloSpringBoot {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Word";
    }

}

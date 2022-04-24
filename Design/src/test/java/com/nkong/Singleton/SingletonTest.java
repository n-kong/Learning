package com.nkong.Singleton;

import org.junit.Test;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/9/9 16:20
 */
public class SingletonTest {

    @Test
    public void test() {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.getValue());
    }

    @Test
    public void test2() {
        String test = Singleton02.getUniqueInstance().test;
        System.out.println(test);
    }
}
